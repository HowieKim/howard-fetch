package com.howie.fetch.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.howie.fetch.R
import com.howie.fetch.data.repo.FetchRepo
import com.howie.fetch.data.repo.models.FetchDomainData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.jetbrains.annotations.VisibleForTesting
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val fetchRepo: FetchRepo) : ViewModel() {

    private val _currentSelectedListId = MediatorLiveData<Int?>()
    val currentSelectedListId: LiveData<Int?> = _currentSelectedListId

    private val _fetchData = MediatorLiveData<List<FetchDomainData>>()

    private val _sortedFetchData = MediatorLiveData<List<FetchDomainData>>()

    val sortedFetchData: LiveData<List<FetchDomainData>> = _sortedFetchData


    private val _showSnackbarError = MutableLiveData<SnackbarMessage>()
    val showSnackbarError: LiveData<SnackbarMessage> = _showSnackbarError

    private val _showLoading = MutableLiveData(false)
    val showLoading: LiveData<Boolean> = _showLoading

    private val _listIdGroups = MutableLiveData<List<Int>>()
    val listIdGroups: LiveData<List<Int>> = _listIdGroups

    init {
        _sortedFetchData.addSource(_currentSelectedListId) { listId ->
            getSortedData(_fetchData.value, listId)?.let {
                _sortedFetchData.value = it
            }
        }

        _sortedFetchData.addSource(_fetchData) { data ->
            getSortedData(data, _currentSelectedListId.value)?.let {
                _sortedFetchData.value = it
            }
        }
        getFetchData()
    }

    @VisibleForTesting
    private fun getFetchData() {
        viewModelScope.launch {
            fetchRepo.getAllData()
                .catch {
                    _showSnackbarError.value =
                        SnackbarMessage(
                            stringId = R.string.uh_oh_something_went_wrong,
                            retryButtonData = ButtonData(
                                buttonLabelStringRes = R.string.retry,
                                onClick = { getFetchData() })
                        )
                }
                .onStart {
                    _showLoading.value = true
                }
                .onCompletion {
                    _showLoading.value = false
                }
                .collect {
                    val filteredData = filterNullData(it)
                    setAllListIdGroups(filteredData)
                    _fetchData.value = filteredData
                }
        }
    }

    fun onGroupChanged(listId: Int?) {
        _currentSelectedListId.value = listId
    }

    private fun setAllListIdGroups(items: List<FetchDomainData>) {
        _listIdGroups.value = items.map { it.listId }.toSet().sorted()
    }

    private fun filterNullData(list: List<FetchDomainData>): List<FetchDomainData> {
        return list.filter { !it.name.isNullOrBlank() }
    }

    private fun getSortedData(
        fetchData: List<FetchDomainData>?,
        currentListId: Int?
    ): List<FetchDomainData>? {
        val finalList = if (currentListId != null) {
            fetchData?.filter { it.listId == currentListId }
        } else fetchData

        return finalList?.sortedWith(compareBy<FetchDomainData> { it.listId }.thenBy {
            it.name?.let { name ->
                Regex("\\d+").find(name)?.value?.toInt() ?: 0
            }
        })
    }

}