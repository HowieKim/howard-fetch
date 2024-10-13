package com.howie.fetch.presentation

import androidx.lifecycle.Observer
import com.howie.fetch.R
import com.howie.fetch.data.repo.FetchRepo
import com.howie.fetch.data.repo.models.FetchDomainData
import com.howie.fetch.utils.InstantTaskExecutorRule
import com.howie.fetch.utils.TestCoroutineExtension
import com.howie.fetch.utils.getOrAwaitValue
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith


@ExtendWith(MockKExtension::class)
@ExtendWith(InstantTaskExecutorRule::class)
@ExtendWith(TestCoroutineExtension::class)
class MainViewModelTest {

    @MockK
    lateinit var fetchRepo: FetchRepo


    @MockK
    lateinit var loadingObserver: Observer<Boolean>

    private lateinit var viewModel: MainViewModel

    private val data = listOf(
        FetchDomainData(id = 1, listId = 2, name = "Item 23"),
        FetchDomainData(id = 2, listId = 3, name = null),
        FetchDomainData(id = 3, listId = 4, name = "Item 21"),
        FetchDomainData(id = 3, listId = 1, name = "Item 40"),
        FetchDomainData(id = 3, listId = 1, name = "Item 20"),
    )

    private val expectedSortedData = listOf(
        FetchDomainData(id = 3, listId = 1, name = "Item 20"),
        FetchDomainData(id = 3, listId = 1, name = "Item 40"),
        FetchDomainData(id = 1, listId = 2, name = "Item 23"),
        FetchDomainData(id = 3, listId = 4, name = "Item 21"),
    )

    private val expectedListIdGroup = listOf(1,2,4)

    private fun initViewModel() {
        viewModel = MainViewModel(fetchRepo)
    }

    @Test
    fun `successful data fetch`() {
        val loadingSequence = mutableListOf<Boolean>()
        every { loadingObserver.onChanged(capture(loadingSequence)) } answers { }
        coEvery { fetchRepo.getAllData() } returns flow { emit(data) }
        initViewModel()
        viewModel.showLoading.observeForever(loadingObserver)
        runTest {
            val actualData = viewModel.sortedFetchData.getOrAwaitValue()
            Assertions.assertEquals(expectedSortedData.size, actualData.size)
            expectedSortedData.forEachIndexed() {index, expected ->
                Assertions.assertEquals(expected.id, actualData[index].id)
                Assertions.assertEquals(expected.listId, actualData[index].listId)
                Assertions.assertEquals(expected.name, actualData[index].name)
            }
            val listIdGroups = viewModel.listIdGroups.getOrAwaitValue()
            expectedListIdGroup.forEachIndexed { index, listId ->
                Assertions.assertEquals(listId, listIdGroups[index])
            }

            Assertions.assertEquals(3, loadingSequence.size)
            Assertions.assertFalse(loadingSequence[0])
            Assertions.assertTrue(loadingSequence[1])
            Assertions.assertFalse(loadingSequence[2])
        }
    }

    @Test
    fun `failed data fetch`() {
        coEvery { fetchRepo.getAllData() } returns flow { throw IllegalStateException() }
        initViewModel()

        runTest {
            Assertions.assertEquals(false, viewModel.sortedFetchData.isInitialized)
            val snackBarMessage = viewModel.showSnackbarError.getOrAwaitValue()

            Assertions.assertEquals(snackBarMessage.stringId, R.string.uh_oh_something_went_wrong)
            Assertions.assertEquals(snackBarMessage.retryButtonData.buttonLabelStringRes, R.string.retry)
        }
    }

    @Test
    fun `on group changed`() {
        coEvery { fetchRepo.getAllData() } returns flow { emit(data) }
        initViewModel()

        runTest {
            viewModel.onGroupChanged(2)
            val currentGroup = viewModel.currentSelectedListId.getOrAwaitValue()
            Assertions.assertEquals(2, currentGroup)
        }
    }
}
