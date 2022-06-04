<template>
  <view class="flex flex-col cross-axis-center">
    <view class="accupy">
      <u-icon name="home"
              size="37"
              @click="toHome"></u-icon>
    </view>
    <u-image width="300rpx"
             height="300rpx"
             src="/static/logo.jpg"
             mode="aspectFit"></u-image>
    <u-button hover-class="none"
              @click="getUserProfile"
              :custom-style="customStyle"
              v-if="!getNewToken">授权登录</u-button>
    <u-button type="warning"
              @click="login"
              :custom-style="customStyle"
              v-else>过期请重新登录</u-button>
  </view>
</template>
<script>
export default {
  onLoad (options) {
    this.getNewToken = options.getNewToken;
    if (this.getNewToken) {
      this.userInfo = uni.getStorageSync('userInfo');
    }
  },
  data () {
    return {
      customStyle: {
        backgroundColor: 'var(--primary-color-1)',
        color: 'white',
        marginTop: '50rpx',
        width: '270rpx',
        height: '70rpx',
      },
      userInfo: {},
      getNewToken: false,
    };
  },
  methods: {
    toHome () {
      console.log(111),
        uni.reLaunch({
          url: "../index/index",
        });
    },
    getUserProfile () {
      uni.getUserProfile({
        desc: '用于首次登陆授权',
        success: (res) => {
          this.userInfo = res.userInfo;
          uni.setStorageSync('userInfo', this.userInfo);
          this.login(false);
        },
        fail: (res) => {
          console.log(res);
        },
      });
    },
    login (getNewToken) {
      uni.login({
        success: (res) => {
          uni.request({
            url: '/user/login/',
            data: {
              code: res.code,
            },
            header: {
              'content-type': 'application/x-www-form-urlencoded',
            },
            method: 'POST',
            success: (res) => {
              console.log(res.data.data.token);
              uni.setStorage({
                key: 'token',
                data: res.data.data.token,
              });
              uni.setStorage({
                key: 'userId',
                data: res.data.data.user_id,
              });
              console.log('开启轮询');
              uni.$emit('login');
              if (!getNewToken) {
                this.updateUserInfo();
              }
              uni.switchTab({
                url: 'index',
                fail: (res) => {
                  console.log(res);
                },
              });
            },
            fail: (res) => {
              console.log(res);
            },
          });
        },
      });
    },
    updateUserInfo () {
      // 更新个人信息
      const Base64 = require('js-base64').Base64;
      modifyUserInformation(
        Base64.encode(this.userInfo.nickName),
        this.userInfo.avatarUrl
      ).then((res) => {
        if (res[1].statusCode === 200) {
          res[1].data.data.nickName = Base64.decode(res[1].data.data.nickName);
          uni.setStorageSync('userInfo', res[1].data.data);
        } else {
          console.log(res);
        }
      });
    },
  },
};
</script>
<style scoped>
.accupy {
  height: 250rpx;
  width: 100vw;
  padding: 10rpx 30rpx;
}
</style>
