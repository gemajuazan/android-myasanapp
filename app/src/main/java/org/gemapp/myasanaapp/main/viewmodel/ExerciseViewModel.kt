package org.gemapp.myasanaapp.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.gemapp.myasanaapp.main.model.*
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel@Inject constructor() : ViewModel() {

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _description = MutableLiveData<String>()
    val description: LiveData<String> = _description

    private val _type = MutableLiveData<TypeExercise>()
    val type: LiveData<TypeExercise> = _type

    private val _position = MutableLiveData<Position>()
    val position: LiveData<Position> = _position

    private val _level = MutableLiveData<Level>()
    val level: LiveData<Level> = _level

    private val _benefits = MutableLiveData<String>()
    val benefits: LiveData<String> = _benefits

    private val _partsOfBody = MutableLiveData<ArrayList<ListItemForm>>()
    val partsOfBody: LiveData<ArrayList<ListItemForm>> = _partsOfBody

    private val _musclesContracted = MutableLiveData<ArrayList<ListItemForm>>()
    val musclesContracted: LiveData<ArrayList<ListItemForm>> = _musclesContracted

    private val _musclesStretched = MutableLiveData<ArrayList<ListItemForm>>()
    val musclesStretched: LiveData<ArrayList<ListItemForm>> = _musclesStretched

    fun setName(name: String) {
        _name.value = name
    }

    fun setDescription(description: String) {
        _description.value = description
    }

    fun setType(type: TypeExercise) {
        _type.value = type
    }

    fun setPosition(position: Position) {
        _position.value = position
    }

    fun setLevel(level: Level) {
        _level.value = level
    }

    fun setBenefits(benefits: String) {
        _benefits.value = benefits
    }

    fun saveExercise() {

    }
}