package org.gemapp.myasanaapp.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import org.gemapp.appilates.R
import org.gemapp.myasanaapp.main.model.ListItemForm
import org.gemapp.myasanaapp.main.ui.Body1
import org.gemapp.myasanaapp.main.ui.Body2
import org.gemapp.myasanaapp.main.ui.Domine
import org.gemapp.myasanaapp.main.viewmodel.ExerciseViewModel

@Composable
fun BoxScope.SeparatorInBox() {
    Divider(
        color = colorResource(id = R.color.divider),
        modifier = Modifier
            .align(Alignment.Center)
            .height(1.dp),
    )
}

@Composable
fun SeparatorInColumn() {
    Divider(
        color = colorResource(id = R.color.divider),
        modifier = Modifier
            .height(1.dp),
    )
}

@Composable
fun AddItemInSectionForm(title: String) {
    Row(Modifier.padding(vertical = 24.dp, horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically) {
        Body1(
            text = title,
            color = colorResource(id = R.color.primary),
            modifier = Modifier
                .weight(1F)
        )
        IconButton(onClick = { }, modifier = Modifier.size(24.dp)) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.add_form),
                contentDescription = "",
                tint = colorResource(id = R.color.primary)
            )
        }
    }
}

@Composable
fun ListItemInSectionForm(viewModel: ExerciseViewModel) {
    val arrayList by viewModel.partsOfBody.observeAsState(initial = arrayListOf())
    if (arrayList.isEmpty()) {
        Body2(text = stringResource(id = R.string.noInformation), modifier = Modifier
            .padding(bottom = 24.dp)
            .fillMaxWidth())
    } else {
        LazyColumn {
            items(arrayList) {
                ItemForm(it)
            }
        }
    }
}

@Composable
fun ItemForm(item: ListItemForm) {
    Row(Modifier.padding(horizontal = 16.dp)) {
        Body1(text = "- ${item.name}", color = colorResource(id = R.color.onSurfaceMedium))
    }
}

@Composable
fun OutlinedFormField(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    singleLine: Boolean = false,
    onChange: (String) -> Unit
) {
    OutlinedTextField(
        label = {
            Text(text = title, fontFamily = Domine)
        },
        value = value,
        onValueChange = {
            onChange(it)
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = colorResource(id = R.color.onSurface),
            backgroundColor = colorResource(id = R.color.surface),
            focusedBorderColor = colorResource(id = R.color.primary),
            unfocusedBorderColor = colorResource(id = R.color.divider),
            placeholderColor = colorResource(id = R.color.onSurfaceMedium),
            unfocusedLabelColor = colorResource(id = R.color.onSurfaceMedium),
            focusedLabelColor = colorResource(id = R.color.primary),
            cursorColor = colorResource(id = R.color.primary)
        ),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier,
        singleLine = singleLine,
        maxLines = if (singleLine) 1 else 10
    )
}