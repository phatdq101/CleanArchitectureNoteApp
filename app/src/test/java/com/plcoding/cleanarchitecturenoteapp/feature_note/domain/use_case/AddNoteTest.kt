package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.use_case

import com.google.common.truth.Truth
import com.plcoding.cleanarchitecturenoteapp.feature_note.data.repository.FakeNoteRepository
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.InvalidNoteException
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class AddNoteTest {

    private lateinit var addNote: AddNote
    private lateinit var fakeNoteRepository: NoteRepository

    @Before
    fun setup() {
        fakeNoteRepository = FakeNoteRepository()
        addNote = AddNote(fakeNoteRepository)
    }

    @Test(expected = InvalidNoteException::class)
    fun insertNodeTitleInvalid_Error() = runBlocking {
        val fakeNote = Note("", "Content", 1L, 1)
        addNote(fakeNote)
    }

    @Test(expected = InvalidNoteException::class)
    fun insertNodeContentInvalid_Error() = runBlocking {
        val fakeNote = Note("Title", "", 1L, 1)
        addNote(fakeNote)
    }

    @Test
    fun insertNodeTitleContentValid_Success() = runBlocking {
        val fakeNote = Note("Title", "Content", 1L, 1)
        Truth.assertThat(addNote(fakeNote)).isInstanceOf(Unit::class.java)
    }
}