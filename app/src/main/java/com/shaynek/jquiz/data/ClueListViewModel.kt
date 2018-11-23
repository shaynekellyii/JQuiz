package com.shaynek.jquiz.data

import androidx.lifecycle.MutableLiveData
import com.shaynek.jquiz.model.Clue
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Observable ViewModel for displaying the list of clues.
 * @constructor Initializes the data status and fetches data from the repository.
 */
class ClueListViewModel : BaseViewModel() {

    @Inject
    lateinit var repository: AppRepository

    val clues: MutableLiveData<List<Clue>> = MutableLiveData()
    val dataStatus: MutableLiveData<DataStatus> = MutableLiveData()

    private val disposable: CompositeDisposable = CompositeDisposable()

    init {
        dataStatus.postValue(DataStatus.LOADING)
        disposable.add(
            repository.fetchClues()
                .subscribe({ clues -> onCluesFetched(clues) }, { e -> onCluesFetchError(e) })
        )
    }

    /**
     * Clears disposables from API calls.
     */
    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    private fun onCluesFetched(fetchedClues: List<Clue>) {
        clues.postValue(fetchedClues)
        dataStatus.postValue(DataStatus.SUCCESS)
    }

    private fun onCluesFetchError(e: Throwable?) {
        dataStatus.postValue(DataStatus.FAILED)
        e?.let { e.printStackTrace() }
    }
}