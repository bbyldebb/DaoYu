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
            <u-avatar :src="postInfo.avatarUrl"
                      :size="90"
                      @click="toUserProfile"></u-avatar>
            <view class="name-time font-small">
              <view class="name">{{ postInfo.nickName }}</view>
              <view class="name-place flex gray-text main-axis-between">
                <view>{{ postInfo.time }}</view>
                <view v-show="
                                        postInfo.place != '我在这里' &&
                                        postInfo.place &&
                                        postInfo.place != ''
                                    ">来自{{ postInfo.place }}</view>
              </view>
            </view>
            <view class="process-view"
                  @click="PostDetail(postInfo)">
              <u-image src="/static/post/unsolved.png"
                       width="95rpx"
                       height="95rpx"
                       v-show="isSolved"></u-image>
              <u-image src="/static/post/solved.png"
                       width="95rpx"
                       height="95rpx"
                       v-show="!isSolved"></u-image>
            </view>
          </view>
          <u-toast ref="uToast" />
        </view>
        <view class="post-content u-line-3 font-small"
              @click="PostDetail(postInfo)">
          {{ postInfo.content }}
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
              v-if="showImage">
          <u-image class="u-image"
                   width="220rpx"
                   height="220rpx"
                   v-for="(image, index) in postInfo.images"
                   :key="image"
                   :src="image"
                   @click="preview(index)"></u-image>
        </view>

        <view class="button flex main-axis-start">

          <view class="button-icon flex">
            <u-icon name="heart"
                    size="37"
                    @click="collect"
                    v-show="!isCollected"></u-icon>
            <view class="font-small"
                  style="margin-left: 7rpx"
                  v-show="!isCollected">
              点击关注
            </view>
            <u-icon name="heart-fill"
                    size="37"
                    @click="cancelCollect"
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
                    @click="PostDetail"></u-icon>
            <view class="font-small">{{ commentNumber }}</view>
          </view>

          <view class="button-icon flex">
            <u-image src="/static/post/hot.png"
                     width="37rpx"
                     height="37rpx"></u-image>
            <view class="font-small"
                  style="margin-left: 7rpx">热度{{postInfo.hotNumber}}</view>
          </view>

          <view class="post-detail"
                @click="detail(postInfo.id)">求助详情 ></view>
        </view>
      </view>
    </u-card>
  </view>
</template>
<script>
export default {
  props: ['postInfo', 'isOpen', 'isPersonHomepage'],
  data () {
    // const Base64 = require('js-base64').Base64;
    return {
      nickName: Base64.decode(this.postInfo.nickName),
      inputContent: null,
      isEditComment: false,
      showHead: false,
      showFoot: false,
      showImage: false,
      commentNumber: 0,
      isCollected: false,
      isSolved: false,
      userId: 0,
      base: baseUrl,
      imageList: [],
      showDelete: false,
    };
  },
  mounted: function () {
    this.loadData();
    for (let index = 0; index < this.postInfo.images.length; index++) {
      this.imageList.push(baseUrl + this.postInfo.images[index].image);
    }
  },
  watch: {
    postInfo: {
      handler () {
        this.loadData();
      },
    },
  },
  methods: {
    loadData (postInfo) {
      if (!postInfo) {
        postInfo = this.postInfo;
      }
      if (typeof postInfo.images !== 'undefined') {
        this.showImage = true;
      }
      this.commentNumber = postInfo.commentNumber;
      this.isSolved = postInfo.solved;
      this.isFollow = postInfo.isFollow;
      this.userId = uni.getStorageSync('userId');
    },
    PostDetail () {
      if (this.isOpen) {
        uni.navigateTo({
          url: `../feedback/feedbackDetail?id=${this.postInfo.id}`,
        });
      } else {
        uni.$emit('focus');
      }
    },
    detail (postID) {
      // if (postID === -1) return;
      // uni.navigateTo({
      //   url: `../feedback/feedbackDetail?id=${postID}`,
      // });
      uni.navigateTo({
        url: `../detail/detail?id=${postID}`,
      });
    },
    preview (index) {
      wx.previewImage({
        current: this.imageList[index].image, // 当前显示图片的http链接
        urls: this.imageList,
      });
    },
    toUserProfile () {
      uni.navigateTo({
        url: `../person/profile`,
      });
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
