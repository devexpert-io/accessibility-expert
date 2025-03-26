package io.devexpert.accessibilityexpert.compose.preview

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Configuration.UI_MODE_TYPE_NORMAL
import androidx.compose.ui.tooling.preview.Preview

@Preview(name = "Phone: Light Mode")
@Preview(name = "Phone: Dark Mode" , uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL)
@Preview(name = "Tablet", device = "spec:width=1280dp,height=800dp,dpi=240")
annotation class MultiPreview