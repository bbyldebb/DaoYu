<template>
  <view>
    <u-card class="card"
            :show-head="false"
            :show-foot="false"
            :border-radius="0"
            margin="0rpx"
            :head-border-bottom="false">
      <view slot="body">
        <view class="content">
          <view class="header flex main-axis-between">
            <view class="avatar-info flex main-axis-between">
              <u-avatar :src="userInfo.avatarImg"
                        :size="80"></u-avatar>
              <view class="name-time font-small">
                <view class="name">{{ userInfo.nickName }}</view>
                <view class="name-place flex gray-text main-axis-between">
                  <view>{{ comment.timeStr }}</view>
                </view>
              </view>
            </view>
          </view>
          <view class="comment-all">
            <view class="comment-content u-line-3 font-small">
              {{ comment.content }}
            </view>
          </view>
        </view>
      </view>
    </u-card>
  </view>
</template>
<script>
import { getUserInfo } from '../js/api'
export default {
  props: ['comment'],
  data () {
    return {
      userInfo: {}
    };
  },
  mounted () {
    getUserInfo(this.comment.userID).then(res => {
      this.userInfo = res[1].data;
    })
  },
  methods: {},
};
</script>
<style scoped>
.avatar-info {
  width: 360rpx;
}
.name-place {
  margin-top: 10rpx;
}
.name {
  font-weight: bold;
}
.comment-all {
  margin-left: 95rpx;
  margin-bottom: 20rpx;
}
.comment-content {
  margin: 0rpx 5rpx;
  margin-bottom: 15rpx;
  line-height: 150%;
}
</style>
