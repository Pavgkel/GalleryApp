package com.example.galleryapp

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Bitmap
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.os.BundleCompat
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CPhoto()
{
    val buttonColors = ButtonDefaults.buttonColors(
        contentColor = Color.White,
        containerColor = MaterialTheme.colorScheme.primary
    )
    val buttonShape = RoundedCornerShape(20)

    val defaultBitmap = ImageBitmap.imageResource(R.drawable.foto)

    //https://developer.android.com/develop/ui/compose/libraries
//    var bitmap by remember { mutableStateOf<ImageBitmap?>(null) }

    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    var bitmap=defaultBitmap
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent? = result.data
            val extras = data?.extras

            val newBitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                data?.getParcelableExtra("data", Bitmap::class.java)
            } else {
                extras?.let {
                    BundleCompat.getParcelable(
                        it, "data",
                        Bitmap::class.java
                    )
                }

            }
            newBitmap?.let {
                //setBitmap(newBitmap)
                bitmap = it.asImageBitmap()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
//            bitmap = bitmap?:defaultBitmap,
            bitmap = bitmap,
            contentDescription = stringResource(id = R.string.app_name),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth(),
        )
        // Spacer to fill up the available space
        //Spacer(modifier = Modifier.weight(1f))

        Button(
            modifier = Modifier
                .height(60.dp)
                .padding(5.dp)
                .fillMaxWidth(),
            colors = buttonColors,
            shape = buttonShape,
            onClick = {
                launcher.launch(intent)
            }) {
            Text("Take photo")
        }
    }
}
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark",
    showBackground = true,
    showSystemUi = true,
    device = "spec:width=411dp,height=891dp,dpi=420,isRound=false,chinSize=0dp,orientation=portrait"
)
@Composable
fun CPreviewPhoto()
{
    CPhoto()
}