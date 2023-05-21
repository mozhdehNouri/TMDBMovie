package com.sample.tmdbmovie.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tmdbmovie.movie.ui.R as MovieResource

@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showBackground = true)
@Composable
fun MovieListScreen() {
   Scaffold(modifier = Modifier.fillMaxSize()) { padding ->


   }

}


@Composable
fun MovieListScreenContent(padding: PaddingValues, movieList: List<String>) {
   Column(Modifier.padding(padding)) {
      Row(modifier = Modifier.weight(1f)) {
         MovieOption(false, "Action")
         MovieOption(true, "Action")
         MovieOption(false, "Action")
      }

      LazyColumn(modifier = Modifier.weight(2f)) {
         item {
            MovieListItem()
         }
      }

   }


}

@Preview(showBackground = true)
@Composable
private fun MovieListItem() {
   Surface() {
      Column(verticalArrangement = Arrangement.Center) {
         Image(
            painter = painterResource(id = MovieResource.drawable.ic_star_rate),
            contentDescription = ""
         )
         Text(text = "India")
         Row() {
            Image(
               painter = painterResource(id = MovieResource.drawable.ic_star_rate),
               contentDescription = ""
            )
            Text(text = "6.7")
         }
      }

   }
}

@Composable
private fun MovieOption(
   selected: Boolean,
   text: String,
   modifier: Modifier = Modifier
) {
   Surface(
      color = when {
         selected -> MaterialTheme.colorScheme.onSurface
         else -> Color.Transparent
      },
      contentColor = when {
         selected -> MaterialTheme.colorScheme.onPrimary
         else -> Color.LightGray
      },
      shape = CircleShape,
      border = BorderStroke(
         width = 1.dp,
         color = when {
            selected -> MaterialTheme.colorScheme.primary
            else -> Color.LightGray
         }
      ),
      modifier = modifier.padding(10.dp)
   ) {
      Text(
         text = text,
         textAlign = TextAlign.Center,
         style = MaterialTheme.typography.bodyMedium,
         modifier = Modifier.padding(8.dp)
      )
   }
}
