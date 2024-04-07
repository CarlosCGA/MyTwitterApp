package com.example.mytwitterapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

const val TWEET_MAX_LENGTH = 139

@Composable
fun Tweet() {

    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.size(16.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.size(16.dp))
            ProfileImage()

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 32.dp)
            ) {
                Spacer(modifier = Modifier.size(4.dp))
                Header()
                Content()
                Spacer(modifier = Modifier.size(16.dp))
                Footer()
            }

        }
        Spacer(modifier = Modifier.size(24.dp))
        Divider(color = Color.Gray)
    }
}

@Composable
fun Header() {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        ProfileNickName()
        Spacer(modifier = Modifier.size(8.dp))
        ProfileUserName()
        Spacer(modifier = Modifier.size(8.dp))
        TimePostedAgo()
        OptionsButton(modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun Content() {
    val text =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum"

    Column(Modifier.fillMaxWidth()) {
        ContentText(text)
        Spacer(modifier = Modifier.size(16.dp))
        ContentImage()
    }
}

@Composable
fun Footer() {
    var howManyComments by rememberSaveable {
        mutableIntStateOf(1)
    }
    var isClickedCommentButton by rememberSaveable {
        mutableStateOf(false)
    }

    var howManyRetweets by rememberSaveable {
        mutableIntStateOf(1)
    }
    var isClickedRetweetButton by rememberSaveable {
        mutableStateOf(false)
    }

    var howManyLikes by rememberSaveable {
        mutableIntStateOf(1)
    }
    var isClickedLikeButton by rememberSaveable {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        CommentsButton(
            modifier = Modifier
                .weight(1F)
                .align(Alignment.CenterVertically),
            howManyComments.toString(),
            isClickedCommentButton
        ) {
            isClickedCommentButton = !isClickedCommentButton
            if (isClickedCommentButton)
                howManyComments++
            else
                howManyComments--
        }
        RetweetButton(
            modifier = Modifier
                .weight(1F)
                .align(Alignment.CenterVertically),
            howManyRetweets.toString(),
            isClickedRetweetButton
        ) {
            isClickedRetweetButton = !isClickedRetweetButton
            if (isClickedRetweetButton)
                howManyRetweets++
            else
                howManyRetweets--
        }

        LikeButton(
            modifier = Modifier
                .weight(1F)
                .align(Alignment.CenterVertically),
            howManyLikes.toString(),
            isClickedLikeButton
        ) {
            isClickedLikeButton = !isClickedLikeButton
            if (isClickedLikeButton)
                howManyLikes++
            else
                howManyLikes--
        }
    }
}

@Composable
fun OptionsButton(modifier: Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.CenterEnd) {
        Icon(
            painter = painterResource(id = R.drawable.ic_dots),
            contentDescription = "Twit options",
            tint = Color.White
        )
    }

}

@Composable
fun ProfileImage() {
    Image(
        modifier = Modifier
            .size(50.dp)
            .clip(CircleShape),
        painter = painterResource(id = R.drawable.profile),
        contentDescription = "Profile image"
    )
}

@Composable
fun ProfileNickName() {
    Text(text = "Aris", fontWeight = FontWeight.Bold, color = Color.White)
}

@Composable
fun ProfileUserName() {
    Text(text = "@AristiDevs", color = Color.Gray)
}

@Composable
fun TimePostedAgo() {
    Text(text = "4h", color = Color.Gray)
}

@Composable
fun ContentText(text: String) {
    val first140Char = text.subSequence(0, TWEET_MAX_LENGTH)
    Text(
        text = first140Char.toString(),
        color = Color.White,
    )
}

@Composable
fun ContentImage() {
    Image(
        modifier = Modifier.clip(RoundedCornerShape(24.dp)),
        painter = painterResource(id = R.drawable.profile),
        contentDescription = "Image"
    )
}

@Composable
fun CommentsButton(
    modifier: Modifier,
    howManyComments: String,
    isClickedCommentsButton: Boolean,
    onClick: () -> Unit
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        val icon = if (isClickedCommentsButton)
            R.drawable.ic_chat_filled
        else
            R.drawable.ic_chat
        Icon(
            modifier = Modifier.clickable { onClick.invoke() },
            painter = painterResource(id = icon),
            contentDescription = "Comments",
            tint = Color.Gray,
        )
        Spacer(modifier = Modifier.size(2.dp))
        Text(text = howManyComments, fontSize = 12.sp, color = Color.Gray)
    }
}

@Composable
fun RetweetButton(
    modifier: Modifier,
    howManyRetweets: String,
    isClickedRetweetButton: Boolean,
    onClick: () -> Unit
) {
    val tint = if (isClickedRetweetButton)
        Color.Green
    else
        Color.LightGray

    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier.clickable { onClick.invoke() },
            painter = painterResource(id = R.drawable.ic_rt),
            contentDescription = "Retweets",
            tint = tint
        )
        Spacer(modifier = Modifier.size(2.dp))
        Text(text = howManyRetweets, fontSize = 12.sp, color = Color.Gray)
    }
}

@Composable
fun LikeButton(
    modifier: Modifier,
    howManyLikes: String,
    isClickedLikeButton: Boolean,
    onClick: () -> Unit
) {
    var icon = R.drawable.ic_like
    var tint = Color.LightGray
    if (isClickedLikeButton) {
        icon = R.drawable.ic_like_filled
        tint = Color.Red
    }

    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier.clickable { onClick.invoke() },
            painter = painterResource(id = icon),
            contentDescription = "Likes",
            tint = tint
        )
        Spacer(modifier = Modifier.size(2.dp))
        Text(text = howManyLikes, fontSize = 12.sp, color = Color.Gray)
    }

}

@Preview(showBackground = true, backgroundColor = 1L)
@Composable
fun PreviewTweet() {
    Tweet()
}

