package org.gemapp.myasanaapp.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import org.gemapp.appilates.R
import org.gemapp.myasanaapp.components.*
import org.gemapp.myasanaapp.main.model.Level
import org.gemapp.myasanaapp.main.model.ListItemForm
import org.gemapp.myasanaapp.main.model.Position
import org.gemapp.myasanaapp.main.model.TypeExercise
import org.gemapp.myasanaapp.main.ui.Body2
import org.gemapp.myasanaapp.main.ui.Domine
import org.gemapp.myasanaapp.main.ui.Header
import org.gemapp.myasanaapp.main.viewmodel.ExerciseViewModel
import java.util.ArrayList

@Composable
fun AddNewItem(
    navigationController: NavHostController, viewModel: ExerciseViewModel
) {
    val scrollState = rememberScrollState()
    val contractedMuscles by viewModel.musclesContracted.observeAsState(initial = arrayListOf())
    val stretchedMuscles by viewModel.musclesStretched.observeAsState(initial = arrayListOf())

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            ToolbarNewItem {
                navigationController.popBackStack()
            }
            Column(
                Modifier
                    .verticalScroll(scrollState)
                    .weight(1F)
            ) {
                AddItemForm(viewModel)
                SeparatorInColumn()
                AddItemInSectionForm(stringResource(id = R.string.partsOfBodyAffected))
                ListItemInSectionForm(viewModel)
                SeparatorInColumn()
                SectionMuscles(contractedMuscles, stretchedMuscles)
            }
            Column {
                SeparatorInColumn()
                Button(
                    onClick = { viewModel.saveExercise() },
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = colorResource(id = R.color.primary),
                        contentColor = colorResource(id = R.color.onPrimary)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.add),
                        fontFamily = Domine,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Composable
fun SectionMuscles(
    contractedMuscles: ArrayList<ListItemForm>,
    stretchedMuscles: ArrayList<ListItemForm>
) {
    AddItemInSectionForm(stringResource(id = R.string.musclesContracted))
    ContractedMuscles(contractedMuscles)
    SeparatorInColumn()
    AddItemInSectionForm(stringResource(id = R.string.musclesStretched))
    StretchedMuscles(stretchedMuscles)
    Box(modifier = Modifier.size(24.dp))
}

@Composable
private fun ContractedMuscles(contractedMuscles: ArrayList<ListItemForm>) {
    Column {
        if (contractedMuscles.isEmpty()) {
            Body2(
                text = stringResource(id = R.string.noInformation),
                modifier = Modifier.padding(bottom = 24.dp).fillMaxWidth()
            )
        } else {
            for (c in contractedMuscles) {
                ItemForm(item = c)
            }
        }
    }
}

@Composable
private fun StretchedMuscles(stretchedMuscles: ArrayList<ListItemForm>) {
    Column {
        if (stretchedMuscles.isEmpty()) {
            Body2(
                text = stringResource(id = R.string.noInformation),
                modifier = Modifier.padding(bottom = 24.dp).fillMaxWidth()
            )
        } else {
            for (c in stretchedMuscles) {
                ItemForm(item = c)
            }
        }
    }
}

@Composable
fun ToolbarNewItem(goBack: () -> Unit) {
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            IconButton(
                onClick = { goBack() }, modifier = Modifier.align(
                    Alignment.CenterStart
                )
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.go_back),
                    modifier = Modifier.size(24.dp),
                    contentDescription = "Go back"
                )
            }
            Header(text = "Nuevo ejercicio", modifier = Modifier.align(Alignment.Center))
        }
        Box(modifier = Modifier.fillMaxWidth()) {
            SeparatorInBox()
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.divider_toolbar),
                modifier = Modifier.align(Alignment.Center),
                contentDescription = ""
            )
        }
    }
}

@Composable
fun AddItemForm(viewModel: ExerciseViewModel) {
    val name by viewModel.name.observeAsState(initial = "")
    val description by viewModel.description.observeAsState(initial = "")
    val type by viewModel.type.observeAsState(initial = TypeExercise.YOGA)
    val position by viewModel.position.observeAsState(initial = Position.SEATED)
    val level by viewModel.level.observeAsState(initial = Level.BASIC)
    val benefits by viewModel.benefits.observeAsState(initial = "")

    Column(Modifier.padding(24.dp)) {
        OutlinedFormField(Modifier.fillMaxWidth(), title = "Nombre", value = name, onChange = {
            viewModel.setName(it)
        })
        OutlinedFormField(
            Modifier
                .fillMaxWidth()
                .height(164.dp)
                .padding(top = 8.dp),
            "Descripción",
            description,
            onChange = {
                viewModel.setDescription(it)
            })
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            OutlinedFormField(Modifier.weight(1F),
                title = "Tipo",
                value = getStringOfType(type),
                onChange = {
                    viewModel.setType(TypeExercise.valueOf(it))
                })
            OutlinedFormField(
                Modifier
                    .weight(1F)
                    .padding(horizontal = 8.dp),
                title = "Postura",
                value = getStringOfPosition(position),
                onChange = {
                    viewModel.setPosition(Position.valueOf(it))
                })
            OutlinedFormField(Modifier.weight(1F),
                title = "Nivel",
                value = getStringOfLevel(level),
                onChange = {
                    viewModel.setLevel(Level.valueOf(it))
                })
        }
        OutlinedFormField(
            Modifier
                .fillMaxWidth()
                .height(164.dp)
                .padding(top = 8.dp),
            "Beneficios",
            benefits,
            onChange = {
                viewModel.setName(it)
            })
    }
}

fun getStringOfType(type: TypeExercise): String {
    return when (type) {
        TypeExercise.YOGA -> "Yoga"
        TypeExercise.PILATES -> "Pilates"
        TypeExercise.MINDFULLNESS -> "Meditación"
    }
}

@Composable
fun getStringOfPosition(position: Position): String {
    return when (position) {
        Position.SEATED -> stringResource(id = R.string.seated)
        Position.STANDING -> stringResource(id = R.string.standing)
        Position.LYING_DOWN -> stringResource(id = R.string.lyingDown)
    }
}

@Composable
fun getStringOfLevel(level: Level): String {
    return when (level) {
        Level.BASIC -> stringResource(id = R.string.basic)
        Level.INTERMEDIATE -> stringResource(id = R.string.intermediate)
        Level.ADVANCED -> stringResource(id = R.string.advanced)
    }
}