<template>
  <view>
    <u-card class="card"
            :show-head="false"
            :show-foot="false"
            :border-radius="0"
            margin="0rpx"
            :head-border-bottom="false">
      <view slot="body">
        <view class="header flex main-axis-between">
          <view class="avatar-info flex main-axis-between">
            <u-avatar :src="postInfo.user.avatarImg"
                      :size="90"
                      @click="toUserProfile()"></u-avatar>
            <view class="name-time font-small"
                  @click="toUserProfile()">
              <view class="name">{{ postInfo.user.nickName }}</view>
              <view class="name-place flex gray-text main-axis-between">
                <view>{{ postInfo.post.timeStr }}</view>
                <view v-show="
                                        postInfo.post.address != '我在这里' &&
                                        postInfo.post.address &&
                                        postInfo.post.address != ''
                                    ">来自{{ postInfo.post.address }}</view>
              </view>
            </view>
            <view class="process-view"
                  @click="modifyProcess()">
              <u-image src="/static/post/unsolved.png"
                       width="98rpx"
                       height="98rpx"
                       v-show="postInfo.post.status!=0"></u-image>
              <u-image src="/static/post/solved.png"
                       width="95rpx"
                       height="95rpx"
                       v-show="postInfo.post.status==0"></u-image>
            </view>
          </view>
          <u-toast ref="uToast" />
        </view>
        <view class="post-content u-line-3 font-small"
              @click="detail()">
          {{ postInfo.post.content }}
        </view>

        <view class="tags flex flex-wrap">
          <u-tag class="u-tag"
                 v-for="topic in postInfo.topics"
                 :key="topic.id"
                 :text="topic.content"
                 type="info"
                 bg-color="var(--primary-color-3)"
                 mode="dark"></u-tag>
        </view>

        <view class="images flex flex-warp"
              v-show="postInfo.post.image">
          <u-image class="u-image"
                   width="220rpx"
                   height="220rpx"
                   :key="image"
                   :src="postInfo.post.image"
                   @click="preview()"></u-image>
        </view>

        <view class="button flex main-axis-start">

          <view class="button-icon flex">
            <u-icon name="heart"
                    size="37"
                    @click="collect()"
                    v-show="!isCollected"></u-icon>
            <view class="font-small"
                  style="margin-left: 7rpx"
                  v-show="!isCollected">
              点击关注
            </view>
            <u-icon name="heart-fill"
                    size="37"
                    @click="cancelCollect()"
                    v-show="isCollected"></u-icon>
            <view class="font-small"
                  style="margin-left: 7rpx"
                  v-show="isCollected">
              已关注
            </view>
          </view>

          <view class="button-icon flex">
            <u-icon name="chat"
                    size="37"
                    @click="comment()"></u-icon>
            <view class="font-small">{{ postInfo.post.commentNum }}</view>
          </view>

          <view class="button-icon flex">
            <u-image src="/static/post/hot.png"
                     width="37rpx"
                     height="37rpx"></u-image>
            <view class="font-small"
                  style="margin-left: 7rpx">{{postInfo.post.hotNum}}</view>
          </view>

          <view class="post-detail"
                v-show="!isOpen"
                @click="detail()">求助详情 ></view>
        </view>
      </view>
    </u-card>
  </view>
</template>
<script>
export default {
  props: ['postInfo', 'isOpen', 'isPersonHomepage'],
  data () {
    return {
      isCollected: false,
      userID: 0,
    };
  },
  methods: {
    modifyProcess () {
      if (!this.isOpen) {
        this.detail();
      }
      else if (uni.getStorageSync('userID') == this.postInfo.post.userID) {
        if (this.postInfo.post.status == 0) {
          this.$refs.uToast.show({
            title: '该求助帖已得到解决',
            type: 'info'
          });
        }
        else {
          this.$emit('modifyProcess');
        }
      }
    },
    comment () {
      if (!this.isOpen) {
        this.detail();
      }
    },
    detail () {
      if (!this.isOpen) {
        uni.navigateTo({
          url: `../detail/detail?postID=${this.postInfo.post.postID}`,
        });
      }
    },
    preview () {
      wx.previewImage({
        current: this.postInfo.post.image, // 当前显示图片的http链接
        urls: [this.postInfo.post.image],
      });
    },
    toUserProfile () {
      if (!this.isOpen) {
        uni.navigateTo({
          url: `../person/profile?userID=${this.postInfo.user.userID}`,
        });
      }
    }
  },
};
</script>
<style scoped>
.avatar-info {
  width: 400rpx;
}
.name-time {
  min-width: 300rpx;
  max-width: 300rpx;
  margin-left: 25rpx;
  font-size: 25rpx;
}
.name-place {
  margin-top: 10rpx;
  flex-direction: column;
  align-items: flex-start;
  font-size: 25rpx;
}
.name {
  font-weight: 600;
}
.process-view {
  margin-left: 180rpx;
}
.post-content {
  margin: 25rpx 5rpx;
  line-height: 150%;
}
.tags {
  width: 100%;
  margin: 5rpx 5rpx 20rpx;
}
.u-tag {
  margin: 5rpx;
}
.images {
  width: 100%;
  margin: 20rpx 0;
}
.u-image {
  margin: 5rpx;
}
.button {
  width: 100%;
}
.button-icon {
  margin-left: 10rpx;
  width: 25%;
}
image {
  width: 120rpx;
  flex: 0 0 120rpx;
  height: 120rpx;
  border-radius: 8rpx;
  margin-left: 12rpx;
}
.absolute-button {
  margin-left: 140rpx;
}
.post-detail {
  font-size: 25rpx;
  margin-top: 5rpx;
  font-weight: bolder;
  color: lightgray;
}
</style>
