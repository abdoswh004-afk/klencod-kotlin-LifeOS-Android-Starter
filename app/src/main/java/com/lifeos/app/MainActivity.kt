package com.lifeos.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Background = Color(0xFF10131B)
private val SurfaceDark = Color(0xFF1B2030)
private val Accent = Color(0xFF8B7CFF)
private val MainText = Color(0xFFF4F2FF)
private val Muted = Color(0xFF9BA3B7)

data class LifeArea(val title: String, val subtitle: String, val icon: androidx.compose.ui.graphics.vector.ImageVector)

private val areas = listOf(
    LifeArea("المهام", "نظّم أعمالك اليومية", Icons.Default.CheckCircle),
    LifeArea("العادات", "تابع عاداتك باستمرار", Icons.Default.Repeat),
    LifeArea("المالية", "الدخل والمصروفات والميزانية", Icons.Default.AccountBalanceWallet),
    LifeArea("الأهداف", "خطط لما تريد تحقيقه", Icons.Default.Flag),
    LifeArea("التقويم", "مواعيدك وأحداثك", Icons.Default.CalendarMonth),
    LifeArea("الصحة", "متابعة صحتك ونشاطك", Icons.Default.Favorite),
    LifeArea("التعلّم", "المهارات والمعرفة", Icons.Default.School),
    LifeArea("المذكرات", "أفكارك ويومياتك", Icons.Default.MenuBook),
    LifeArea("المشاريع", "خطط ومتابعة المشاريع", Icons.Default.Work),
    LifeArea("الأعمال", "إدارة أعمالك", Icons.Default.Store),
    LifeArea("التحليلات", "راجع تقدمك وأرقامك", Icons.Default.Insights),
    LifeArea("المعرفة", "احفظ معلوماتك المهمة", Icons.Default.Lightbulb),
    LifeArea("الإعدادات", "خصّص تجربتك", Icons.Default.Settings)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { LifeOsApp() }
    }
}

@Composable
fun LifeOsApp() {
    var selected by remember { mutableStateOf("الرئيسية") }
    MaterialTheme(colorScheme = darkColorScheme(
        background = Background, surface = SurfaceDark, primary = Accent,
        onBackground = MainText, onSurface = MainText
    )) {
        Row(Modifier.fillMaxSize().background(Background)) {
            Column(
                Modifier.weight(1f).fillMaxHeight().padding(horizontal = 18.dp, vertical = 22.dp)
            ) {
                Text("LIFE OS", color = Accent, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))
                Text(selected, color = MainText, fontSize = 27.sp, fontWeight = FontWeight.Bold)
                Text("مساحة واحدة لتنظيم حياتك", color = Muted, fontSize = 13.sp)
                Spacer(Modifier.height(22.dp))
                if (selected == "الرئيسية") {
                    DashboardContent(onOpen = { selected = it })
                } else {
                    AreaContent(selected)
                }
            }
        }
    }
}

@Composable
private fun DashboardContent(onOpen: (String) -> Unit) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        item {
            Card(colors = CardDefaults.cardColors(containerColor = SurfaceDark), shape = RoundedCornerShape(20.dp)) {
                Column(Modifier.fillMaxWidth().padding(18.dp)) {
                    Text("أهلاً بك في Life OS", color = MainText, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(6.dp))
                    Text("ابدأ بتنظيم يومك، وستظهر ملخصاتك هنا مع تطور التطبيق.", color = Muted, fontSize = 13.sp)
                }
            }
        }
        item { Text("مساحات حياتك", color = MainText, fontSize = 18.sp, fontWeight = FontWeight.SemiBold) }
        items(areas.size) { index ->
            val area = areas[index]
            Card(
                modifier = Modifier.fillMaxWidth().clickable { onOpen(area.title) },
                colors = CardDefaults.cardColors(containerColor = SurfaceDark),
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(area.icon, contentDescription = null, tint = Accent)
                    Spacer(Modifier.width(14.dp))
                    Column(Modifier.weight(1f)) {
                        Text(area.title, color = MainText, fontWeight = FontWeight.SemiBold)
                        Text(area.subtitle, color = Muted, fontSize = 12.sp)
                    }
                    Icon(Icons.Default.ChevronLeft, contentDescription = null, tint = Muted)
                }
            }
        }
    }
}

@Composable
private fun AreaContent(title: String) {
    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Card(colors = CardDefaults.cardColors(containerColor = SurfaceDark), shape = RoundedCornerShape(18.dp)) {
            Column(Modifier.fillMaxWidth().padding(18.dp)) {
                Text(title, color = MainText, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))
                Text("هذه مساحة مبدئية. سنضيف وظائفها وقاعدة بياناتها في مراحل التنفيذ التالية.", color = Muted)
            }
        }
        Text("حالة الوحدة: قيد التجهيز", color = Accent, fontSize = 13.sp)
    }
}