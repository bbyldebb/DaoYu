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
              v-if="getNewToken">授权登录</u-button>
    <u-button hover-class="none"
              @click="getUserProfile"
              :custom-style="customStyle"
              v-if="!getNewToken">您已登录，无需授权</u-button>
    <u-toast ref="uToast" />
  </view>
</template>
<script>
import { login } from '../../js/api';
export default {
  onLoad () {
    this.userInfo = uni.getStorageSync('userInfo');
    this.userID = uni.getStorageSync('userID');
    if (this.userInfo != '' && this.userID != '') this.getNewToken = false;
  },
  data () {
    return {
      customStyle: {
        backgroundColor: 'var(--primary-color-1)',
        color: 'white',
        marginTop: '50rpx',
        width: '300rpx',
        height: '70rpx',
      },
      userInfo: {},
      userID: null,
      getNewToken: true,
    };
  },
  methods: {
    toHome () {
      uni.reLaunch({
        url: "../index/index",
      });
    },
    getUserProfile () {
      if (this.getNewToken == true) {
        uni.getUserProfile({
          desc: '用于首次登陆授权',
          success: (res) => {
            this.userInfo = res.userInfo;
            uni.setStorageSync('userInfo', this.userInfo);
            this.login();
          },
          fail: (res) => {
            console.log(res);
          },
        });
      }
      else {
        this.$refs.uToast.show({
          title: '您已授权登录，\n无需再次授权',
          type: 'success',
        });
        uni.$emit('login');
        setTimeout(() => {
          this.toHome();
        }, 1000);
      }
    },
    login () {
      uni.login({
        success: (res) => {
          uni.request({
            url: `https://api.weixin.qq.com/sns/jscode2session?appid=wx672dc2b334d3b9ae&secret=93a88d99d1a39698f9715ab1b5f1db19&js_code=${res.code}&grant_type=authorization_code`,
            method: 'POST',
            success: (res) => {
              uni.setStorageSync('userID', res.data.openid);
              login(res.data.openid, this.userInfo.nickName, this.userInfo.avatarUrl).then((res) => {
                this.$refs.uToast.show({
                  title: '登录成功',
                  type: 'success',
                });
                uni.$emit('login');
                setTimeout(() => {
                  this.toHome();
                }, 700);
              });
            },
            fail: (res) => {
              console.log(res);
            },
          });
        },
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
