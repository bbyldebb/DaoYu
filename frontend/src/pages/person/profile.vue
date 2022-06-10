<template>
  <scroll-view class="content flex main-axis-center">
    <view class="avator-bar flex main-axis-center cross-axis-center">
      <u-image width="150rpx"
               height="150rpx"
               shape="circle"
               :src="person.avatarImg"></u-image>
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
                     src="/static/person/profile/sex.png"></u-image>
          </view>
          <text>性别：{{ person.sex }}</text>
        </view>
        <view class="birth-bar bar">
          <view class="icon">
            <u-image width="35rpx"
                     height="35rpx"
                     src="/static/person/profile/birth.png"></u-image>
          </view>
          <text>生日：{{ person.birthday }}</text>
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
                     src="/static/person/profile/place.png"></u-image>
          </view>
          <text>电话：{{ person.phoneNum }}</text>
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
                     src="/static/person/profile/place.png"></u-image>
          </view>
          <text>地址：{{ person.address }}</text>
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
                     src="/static/person/profile/intro.png"></u-image>
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
import { getUserInfo } from '../../js/api'
export default {
  data () {
    return {
      person: [],
    };
  },
  onLoad (options) {
    getUserInfo(options.userID).then((res) => {
      if (res[1].statusCode === 200) {
        this.person = res[1].data;
        let str = '他';
        this.person.sex = '男';
        if (this.person.gender === 2) {
          str = '她';
          this.person.sex = '她';
        }
        if (this.person.introduction == null) {
          this.person.introduction = '这个人很神秘，什么都没有写';
        }
        if (this.person.phoneNum == null) {
          this.person.phoneNum = str + '还没有填写电话号码';
        }
        if (this.person.address == null) {
          this.person.address = str + '还没有填写地址';
        }
        if (this.person.birthday == null) {
          this.person.birthday = str + '还没有填写生日';
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

.line {
  width: 100%;
  height: 0.3rpx;
  background-color: rgba(192, 192, 192, 0.3);
}
</style>
