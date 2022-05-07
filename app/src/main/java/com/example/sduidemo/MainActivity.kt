package com.example.sduidemo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.sduidemo.ui.theme.SDUIDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SDUIDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column() {
                        val list = dataFromServer()

                        repeat(list.size) {
                            DynamicUI(component = list[it])
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DynamicUI(component: ComponentToRender) {
    when (component.viewType) {
        "TopBar" -> TopAppbar(component = component)
        "GridView" -> GridView(component = component)
    }

}

// Dynamic view
@Composable
fun TopAppbar(component: ComponentToRender) {
    TopAppBar(
        title = {
            Text(
                text = component.data.title,
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            )
        },
        backgroundColor = Color.Red
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GridView(component: ComponentToRender) {
    val context = LocalContext.current

    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp)
    ) {
        // You pass the list of colors in `items`
        // and then in lambda you get
        // each color from the list.
        items(component.data.data.size) { item ->
            Card(
                elevation = 4.dp,
                modifier = Modifier
                    .padding(4.dp)
                    // width is handled by LazyVerticalGrid
                    // it is important for you to specify the height.
                    .height(component.data.data[item].cardHeight.dp),

                // here you change the background with each color from the list
                backgroundColor = Color(color = component.data.data[item].color.toColorInt()),
                //backgroundColor = Color.Blue,
                //shape = RectangleShape
            shape = if (component.data.data[item].shape == "circle") CircleShape else RectangleShape
            ) {
                // add your content
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .border(1.dp, Color.Transparent, CircleShape)
                        .fillMaxWidth()
                        .padding(2.dp)
                ) {

                    Text(text = "${component.data.data[item].name}",
                        fontSize = component.data.data[item].fontSize.sp,
                        textAlign = TextAlign.Center,
                        color = colorResource(id = R.color.white),
                        modifier = Modifier
                            .clickable {
                                Toast
                                    .makeText(context, "${component.data.data[item].name} clicked", Toast.LENGTH_SHORT)
                                    .show()
                            }
                            .fillMaxSize()
                            .wrapContentSize())
                }
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SDUIDemoTheme {
        val state = rememberScaffoldState()
        Scaffold(
            scaffoldState = state
        ) {

            Column() {
                val list = dataFromServer()

                repeat(list.size) {
                    DynamicUI(component = list[it])
                }
            }
        }
    }
}

fun dataFromServer(): List<ComponentToRender> {
    return listOf (
        ComponentToRender(
            "TopBar",
            data = ElementData("TopBar", "Server Driven UI", data = listOf())
        ),
        ComponentToRender(
            "GridView", data = ElementData(
                viewType = "GridView", title = "", data = listOf(
                    ItemData(
                        shape = "circle",
                        color = "#00ff00",
                        name = "Item0",
                        cardHeight = 180,
                        fontSize = 32
                    ),
                    ItemData(
                        shape = "rectangle",
                        color = "#00ffff",
                        name = "Item1",
                        cardHeight = 180,
                        fontSize = 32
                    ),
                    ItemData(
                        shape = "rectangle",
                        color = "#ffff00",
                        name = "Item2",
                        cardHeight = 180,
                        fontSize = 32
                    ),
                    ItemData(
                        shape = "circle",
                        color = "#ffffff",
                        name = "Item3",
                        cardHeight = 180,
                        fontSize = 32
                    ),
                    ItemData(
                        shape = "circle",
                        color = "#00ff00",
                        name = "Item4",
                        cardHeight = 180,
                        fontSize = 32
                    ),
                    ItemData(
                        shape = "rectangle",
                        color = "#00ffff",
                        name = "Item5",
                        cardHeight = 180,
                        fontSize = 32
                    ),
                    ItemData(
                        shape = "rectangle",
                        color = "#ffff00",
                        name = "Item6",
                        cardHeight = 180,
                        fontSize = 32
                    ),
                    ItemData(
                        shape = "circle",
                        color = "#ffffff",
                        name = "Item7",
                        cardHeight = 180,
                        fontSize = 32
                    ),
                    ItemData(
                        shape = "circle",
                        color = "#00ff00",
                        name = "Item8",
                        cardHeight = 180,
                        fontSize = 32
                    ),
                    ItemData(
                        shape = "rectangle",
                        color = "#ffff00",
                        name = "Item9",
                        cardHeight = 180,
                        fontSize = 32
                    ),
                    ItemData(
                        shape = "rectangle",
                        color = "#00ffff",
                        name = "Item10",
                        cardHeight = 180,
                        fontSize = 32
                    ),
                    ItemData(
                        shape = "circle",
                        color = "#ffffff",
                        name = "Item11",
                        cardHeight = 180,
                        fontSize = 32
                    ),
                    ItemData(
                        shape = "circle",
                        color = "#00ff00",
                        name = "Item12",
                        cardHeight = 180,
                        fontSize = 32
                    ),
                    ItemData(
                        shape = "rectangle",
                        color = "#ffff00",
                        name = "Item13",
                        cardHeight = 180,
                        fontSize = 32
                    ),
                    ItemData(
                        shape = "rectangle",
                        color = "#00ffff",
                        name = "Item14",
                        cardHeight = 180,
                        fontSize = 32
                    ),
                    ItemData(
                        shape = "circle",
                        color = "#000000",
                        name = "Item15",
                        cardHeight = 180,
                        fontSize = 32
                    ),
                    ItemData(
                        shape = "circle",
                        color = "#00ff00",
                        name = "Item16",
                        cardHeight = 180,
                        fontSize = 32
                    ),
                    ItemData(
                        shape = "rectangle",
                        color = "#00ffff",
                        name = "Item17",
                        cardHeight = 180,
                        fontSize = 32
                    ),
                    ItemData(
                        shape = "rectangle",
                        color = "#ffff00",
                        name = "Item18",
                        cardHeight = 180,
                        fontSize = 32
                    ),
                    ItemData(
                        shape = "circle",
                        color = "#000000",
                        name = "Item19",
                        cardHeight = 180,
                        fontSize = 32
                    )


                )
            )
        )
    )
}

data class ComponentToRender(val viewType: String, val data: ElementData)
data class ElementData(val viewType: String, val title: String, val data: List<ItemData>)
data class ItemData(
    val shape: String,
    val color: String,
    val name: String,
    val cardHeight: Int,
    val fontSize: Int
)
