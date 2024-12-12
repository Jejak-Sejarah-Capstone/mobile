package com.example.jejak_sejarah.ui.profile

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jejak_sejarah.R
import com.example.jejak_sejarah.ui.leaderboard.LeaderboardScreen
import com.example.jejak_sejarah.ui.theme.JejaksejarahTheme
import com.example.jejak_sejarah.ui.theme.darkBrown
import drawable.Trophy

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)){
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .padding(top = 40.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.imageprofile), // Ganti dengan nama file foto profil Anda
                    contentDescription = "profile picture",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.White, CircleShape)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Nama Anda",
                    style = MaterialTheme.typography.headlineSmall,
                    color = darkBrown,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
        Column {
            OutlinedCard( colors = CardDefaults.cardColors(
                containerColor = darkBrown
            ),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 12.dp)){
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Absolute.SpaceBetween, verticalAlignment = Alignment.CenterVertically)  {
                    Row(modifier = Modifier
                        .padding(10.dp)
                        .padding(start = 5.dp),
                        verticalAlignment = Alignment.CenterVertically,

                    ) {

                        Icon(
                            imageVector = Icons.Filled.Email,
                            contentDescription = "Email Icon",
                            tint = Color.White,
                            modifier = Modifier.size(34.dp)
                        )
                        Text(
                            text = "Email",
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 24.sp,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
            OutlinedCard( colors = CardDefaults.cardColors(
                containerColor = darkBrown
            ),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 12.dp)){
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Absolute.SpaceBetween, verticalAlignment = Alignment.CenterVertically)  {
                    Row(modifier = Modifier
                        .padding(10.dp)
                        .padding(start = 5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Absolute.SpaceBetween
                    ) {
                        Row(){
                            Icon(
                                imageVector = Trophy,
                                contentDescription = "trophy Icon",
                                tint = Color.White,
                                modifier = Modifier.size(34.dp)
                            )
                            Text(
                                text = "#1",
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 24.sp,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                        Text(
                            text = "2000Pts",
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 24.sp,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    JejaksejarahTheme {
        ProfileScreen(modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()))
    }
}

