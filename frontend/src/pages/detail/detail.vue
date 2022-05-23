<template>
  <view>

    <scroll-view class="background"
                 scroll-y="true">

      <!-- 帖子 -->
      <view style="height: 60%;width: 100%;/* background-color: pink; */">

        <!-- 头像 -->
        <image src="../../static/logo.png"
               class="profile"></image>

        <!-- ID -->
        <text class="userID">小冯来一口锅包肉</text>

        <!-- 具体内容 -->
        <view class="content">
          <text class="detailText">
            11
          </text>
          <image src="../../static/logo.png"
                 class="detailImg"></image>
        </view>

        <!-- 帖子信息 -->
        <text class="info">2022年5月22日 13:17</text>

        <!-- 关注、评论 -->
        <image class="likeButton"
               src="../../static/like.png"></image>
        <image class="commentButton"
               src="../../static/comment.png"></image>
      </view>

      <!-- 评论区 -->
      <view class="commentArea">
        <!-- 评论 -->
        <ul>
          <li v-for="item in comments"
              :key="item.id"
              class="comment">
            <!-- 头像 -->
            <image class="commentProfile"
                   :src="item.profile"></image>
            <!-- ID -->
            <text class="commentID">{{item.id}}</text>
            <!-- 评论内容 -->
            <text class="commentContent">{{item.content}}</text>
          </li>
        </ul>
      </view>
    </scroll-view>

    <!-- 输入框 -->
    <view class="keybordArea"
          style="background-color:whitesmoke">
      <input placeholder="发送评论"
             style="position:absolute;
						 top: 25rpx;
						 left:30rpx;
						 height:20rpx;
						 width: 550rpx;
						 border-radius: 10rpx;
						 border-style:groove;">
      <button style="position:absolute;
								top: 25rpx;
								right:30rpx;
								height:60rpx;
								width: 120rpx;
								font-size: 25rpx;
								text-align:center;">发送</button>
    </view>
  </view>

</template>

<script>
export default {
  data () {
    return {
      show: false,
      comments: [
        {
          profile: "../../static/logo.png",
          id: "冰冰",
          content: "我是一条评论"
        },
        {
          profile: "../../static/logo.png",
          id: "冰冰",
          content: "我是一条评论"
        },
        {
          profile: "../../static/logo.png",
          id: "冰冰",
          content: "我是一条评论"
        }
      ]
    }
  },
  methods: {

  }
}
</script>

<style>
.background {
  height: 100vh;
  width: 100vw;
  /* background-color: pink; */
}

/* 帖子 */
.profile {
  position: absolute;
  height: 100rpx;
  width: 100rpx;
  background-color: red;
  top: 30rpx;
  left: 30rpx;
  border-radius: 30rpx;
}

.userID {
  position: absolute;
  height: 40rpx;
  width: 400rpx;
  /* background-color: green; */
  top: 50rpx;
  left: 150rpx;
  font-size: medium;
  color: black;
}

.content {
  position: absolute;
  height: 500rpx;
  width: 560rpx;
  /* background-color: red; */
  top: 120rpx;
  left: 140rpx;
  border-style: solid;
  border-color: whitesmoke;
}

.detailText {
  position: absolute;
  height: 250rpx;
  width: 460rpx;
  /* border-style: groove; */
  top: 20rpx;
  left: 20rpx;
  font-family: 'Times New Roman', Times, serif;
  font-size: medium;
  text-align: left;
}

.detailImg {
  position: absolute;
  height: 180rpx;
  width: 180rpx;
  /* border-style: groove; */
  top: 290rpx;
  left: 20rpx;
}

.info {
  position: absolute;
  height: 40rpx;
  width: 300rpx;
  /* background-color: green; */
  top: 645rpx;
  left: 160rpx;
  font-size: 25rpx;
  color: grey;
}

.likeButton {
  position: absolute;
  height: 50rpx;
  width: 50rpx;
  /* background-color: green; */
  top: 635rpx;
  left: 600rpx;
}

.commentButton {
  position: absolute;
  height: 50rpx;
  width: 50rpx;
  /* background-color: green; */
  top: 635rpx;
  left: 675rpx;
}

/* 评论区 */
.commentArea {
  position: absolute;
  height: 400rpx;
  width: 750rpx;
  /* background-color: green; */
  top: 700rpx;
}

.comment {
  position: relative;
  height: 100rpx;
  width: 690rpx;
  /* left: 30rpx; */
  margin: 30rpx;
  background-color: whitesmoke;
}

.commentProfile {
  position: absolute;
  height: 70rpx;
  width: 70rpx;
  top: 15rpx;
  left: 15rpx;
  /* background-color: red; */
  border-radius: 20rpx;
}

.commentID {
  position: absolute;
  height: 25rpx;
  width: 400rpx;
  top: 10rpx;
  left: 100rpx;
  /* background-color: red; */
}

.commentContent {
  position: absolute;
  height: 40rpx;
  width: 400rpx;
  top: 50rpx;
  left: 100rpx;
  /* background-color: red; */
  font-size: 30rpx;
}

/* 输入框 */
.keybordArea {
  position: absolute;
  height: 100rpx;
  width: 750rpx;
  bottom: 0;
  font-size: 30rpx;
  color: pink;
}

.keybordButton {
  position: absolute;
  height: 20rpx;
  width: 20rpx;
  top: 10rpx;
  left: 10rpx;
  font-size: 30rpx;
  color: red;
}
</style>