<template>
  <scroll-view class="content flex main-axis-center">
    <view class="avator-bar flex main-axis-center cross-axis-center">
      <u-image width="150rpx"
               height="150rpx"
               shape="circle"
               :src="person.avatarUrl"></u-image>
    </view>
    <view class="name-bar flex main-axis-center">
      <text>{{ person.nickName }}</text>
    </view>

    <view class="info">
      <view class="title-bar-container bar-container">
        <text>基础信息</text>
      </view>

      <view class="sex-birth-bar-container bar-container">
        <view class="sex-bar bar">
          <view class="icon">
            <u-image width="40rpx"
                     height="40rpx"
                     src="/static/details/sex.png"></u-image>
          </view>
          <text>性别：{{ person.sex }}</text>
        </view>
        <view class="birth-bar bar">
          <view class="icon">
            <u-image width="35rpx"
                     height="35rpx"
                     src="/static/details/birth.png"></u-image>
          </view>
          <text>生日：{{ person.birth }}</text>
        </view>
      </view>
      <view class="division-line">
        <view class="line"></view>
      </view>

      <view class="fans-follow-likes-bar-container bar-container">
        <view class="fans-bar bar">
          <view class="icon">
            <u-image width="40rpx"
                     height="40rpx"
                     src="/static/details/fans.png"></u-image>
          </view>
          <text>粉丝：{{ person.fans }}</text>
        </view>
        <view class="follow-bar bar">
          <view class="icon">
            <u-image width="40rpx"
                     height="40rpx"
                     src="/static/details/follow.png"></u-image>
          </view>
          <text>关注：{{ person.followers }}</text>
        </view>
        <view class="likes-bar bar">
          <view class="icon">
            <u-image width="37rpx"
                     height="37rpx"
                     src="/static/details/likes.png"></u-image>
          </view>
          <text>获赞：{{ person.likes }}</text>
        </view>
      </view>
      <view class="division-line">
        <view class="line"></view>
      </view>

      <view class="place-bar-container bar-container">
        <view class="place-bar bar">
          <view class="icon">
            <u-image width="38rpx"
                     height="38rpx"
                     src="/static/details/place.png"></u-image>
          </view>
          <text>地址：{{ person.place }}</text>
        </view>
      </view>
      <view class="division-line">
        <view class="line"></view>
      </view>

      <view class="identity-bar-container bar-container">
        <view class="identity-bar bar">
          <view class="icon">
            <u-image width="33rpx"
                     height="33rpx"
                     src="/static/details/identity.png"></u-image>
          </view>
          <text>身份：{{ person.identity }}</text>
        </view>
      </view>
      <view class="division-line">
        <view class="line"></view>
      </view>

      <view class="intro-bar-container bar-container">
        <view class="intro-bar bar">
          <view class="icon">
            <u-image width="33rpx"
                     height="33rpx"
                     src="/static/details/intro.png"></u-image>
          </view>
          <text>简介：{{ person.introduction }}</text>
        </view>
      </view>
      <view class="division-line flex">
        <view class="line"></view>
      </view>
    </view>
  </scroll-view>
</template>

<script>
export default {
  data () {
    return {
      person: [],
      userID: null,
    };
  },
  onLoad (options) {
    this.userID = options.userID;
    getOtherUserInformation(this.userID).then((res) => {
      if (res[1].statusCode === 200) {
        this.person = res[1].data.data;
        // const Base64 = require('js-base64').Base64;
        this.person.nickName = Base64.decode(this.person.nickName);
        let str = '她';
        if (this.person.sex === '男') {
          str = '他';
        }
        if (this.person.introduction === '') {
          this.person.introduction = '这个人很神秘，什么都没有写';
        }
        if (this.person.place === '') {
          this.person.place = str + '还没有填写地址';
        }
        if (this.person.birth == null) {
          this.person.birth = str + '还没有填写生日';
        }
      } else {
        console.log(res[1].data.data);
      }
    });
  },
  methods: {},
};
</script>

<style scoped>
page {
  height: 100%;
}

.content {
  width: 100%;
  height: 100%;
}

.avator-bar {
  height: 180rpx;
  display: flex;
}

.name-bar {
  font-size: 35rpx;
  font-weight: bold;
}

.info {
  width: 92%;
  height: 100%;
  margin: auto;
  font-size: 30rpx;
  color: #626262;
}

.bar-container {
  width: 100%;
  height: 100rpx;
  display: flex;
  flex-direction: row;
  align-items: center;
}

.bar {
  display: flex;
  flex-direction: row;
}

.icon {
  width: 70rpx;
  display: flex;
  justify-content: center;
  align-items: center;
}

.title-bar-container {
  width: 95%;
  color: rgba(192, 192, 192);
  font-weight: bold;
  font-size: 30rpx;
  margin: auto;
}

.sex-bar {
  width: 40%;
}

.birth-bar {
  width: 60%;
}

.fans-bar {
  width: 33%;
}

.follow-bar {
  width: 33%;
}

.likes-bar {
  width: 33%;
}

.line {
  width: 100%;
  height: 0.3rpx;
  background-color: rgba(192, 192, 192, 0.3);
}
</style>
