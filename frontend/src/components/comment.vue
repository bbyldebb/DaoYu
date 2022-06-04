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
              <u-avatar :src="comment.avatarUrl"
                        :size="80"></u-avatar>
              <view class="name-time font-small">
                <view class="name">{{ this.Base64.decode(comment.nickName) }}</view>
                <view class="name-place flex gray-text main-axis-between">
                  <view>{{ comment.time }}</view>
                </view>
              </view>
            </view>
            <view class="trash-icon">
              <u-icon name="trash"
                      size="35"
                      @click="deleteButton(comment.id)"
                      v-if="isMine"></u-icon>
            </view>
          </view>
          <view class="comment-all">
            <view class="comment-content u-line-3 font-small">
              {{ comment.content }}
            </view>
            <view class="sub-comment"
                  v-if="subcomment.length">
              <view class="sub-comment-item flex font-small"
                    v-for="commentItem in subcomment.slice(0, 2)"
                    :key="commentItem">
                <view class="comment-item-nikname">
                  {{ Nickname(commentItem.nickName) }}:
                </view>
                <view>{{ commentItem.content }}</view>
              </view>
              <view class="answer-text font-small"
                    @click="commentDetails(comment.id)">
                共{{ subcomment.length }}条回复&nbsp;>
              </view>
            </view>
          </view>
        </view>
        <view class="button flex main-axis-end">
          <view class="button-like button-icon flex">
            <u-icon name="thumb-up-fill"
                    size="35"
                    @click="cancelLike"
                    v-if="isLike"></u-icon>
            <u-icon name="thumb-up"
                    size="35"
                    @click="like"
                    v-else></u-icon>
            <view class="font-small">{{ likeNumber }}</view>
          </view>
          <view>
            <u-icon name="chat"
                    size="35"
                    @click="commentDetails(comment.id)"
                    v-if="isParentComment"></u-icon>
          </view>
        </view>
        <u-toast ref="uToast" />
      </view>
    </u-card>
  </view>
</template>
<script>
export default {
  props: {
    postID: String,
    comment: {
      id: 1,
      avatarUrl: '',
      nickName: '小奇的日记',
      time: '22:10',
      content: '',
      userId: '',
    },
    subcomment: Array,
    // eslint-disable-next-line vue/require-prop-type-constructor
    isParentComment: true,
  },
  mounted () {
    const myId = uni.getStorageSync('userId');
    if (myId === parseInt(this.comment.userId)) {
      this.isMine = 1;
    } else {
      this.isMine = 0;
    }
  },
  data () {
    return {
      Base64: require('js-base64').Base64,
      inputContent: null,
      // TODO 待更新功能：点赞
      isEditSubComment: false,
      isLike: false,
      likeNumber: '24',
      isMine: 1,
    };
  },
  methods: {
    cancelLike () {
      this.isLike = false;
      this.likeNumber = this.likeNumber - '0' - 1;
    },
    like () {
      this.isLike = true;
      this.likeNumber = this.likeNumber - '0' + 1;
    },
    commentDetails (commentID) {
      uni.navigateTo({
        url: `../comment/commentDetails?postId=${this.postID}&commentId=${commentID}`,
      });
    },
    deleteButton (commentId) {
      if (this.isParentComment === true) {
        deleteComment(commentId).then((res) => {
          if (res[1].statusCode === 200) {
            this.$emit('deleteComment', commentId);
          } else {
            console.log(res);
            this.$emit('deleteComment', -1);
          }
        });
      } else {
        deleteSubcomment(commentId).then((res) => {
          if (res[1].statusCode === 200) {
            this.$emit('deleteSubcomment', commentId);
          } else {
            console.log(res);
            this.$emit('deleteSubcomment', -1);
          }
        });
      }
    },
  },
};
</script>
<style scoped>
.avatar-info {
  width: 360rpx;
}
.name-time {
  width: 260rpx;
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
.sub-comment {
  background-color: var(--background);
  padding: 10rpx;
}
.sub-comment-item {
  margin-bottom: 10rpx;
}
.comment-item-nikname {
  color: var(--blue);
  margin-right: 10rpx;
}
.answer-text {
  color: var(--blue);
}
.button {
  width: 100%;
}
.button-like {
  display: none;
  width: 100rpx;
}
</style>
