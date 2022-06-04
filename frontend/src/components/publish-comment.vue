<template>
  <view class="publish-comment flex main-axis-between">
    <u-input class="input"
             @blur="watchInputBlur"
             ref="inputVal"
             :placeholder="placeholder"
             :adjust-position="false"
             :focus="ifFocus"
             :value="inputContent"
             @input="inputChange"
             type="textarea"
             height="52"
             :clearable="false"
             :auto-height="true"
             :maxlength="1000"
             :border="false"></u-input>

    <view class="confirm-btn flex cross-axis-center">
      <u-button :custom-style="customStyle"
                hover-class="none"
                @click="submit"
                :disabled="inputContent === ''">提交</u-button>
    </view>
    <u-toast ref="uToast" />
  </view>
</template>
<script>
export default {
  props: {
    postID: {
      type: Number,
      required: true,
    },
    commentID: {
      type: Number,
    },
    placeholder: {
      type: String,
    },
    type: {
      type: String,
    },
  },
  data () {
    return {
      customStyle: {
        backgroundColor: 'var(--primary-color-1)',
        color: 'white',
        marginRight: '20rpx',
        marginLeft: '10rpx',
        height: '70rpx',
      },
      ifFocus: false,
      inputContent: '',
    };
  },
  methods: {
    watchInputBlur () {
      this.ifFocus = false;
    },
    inputChange (e) {
      this.inputContent = e;
    },
    getFocus () {
      this.ifFocus = true;
    },
    submit () {
      if (this.type === 'postComment' || this.type === 'feedbackComment')
        this.submitComment();
      if (this.type === 'feedbackReply') this.submitFeedbackReply();
    },
    submitFeedbackReply () {
      addFeedbackReply(this.postID, this.inputContent).then((res) => {
        if (res[1].statusCode === 200) {
          this.inputContent = '';
          this.$emit('refreshFeedback');
          this.$refs.uToast.show({
            title: '回复成功',
          });
        } else console.log(res);
      });
    },
    submitComment () {
      if (this.commentID !== 0) {
        addPostSubComment(this.commentID, this.inputContent).then((res) => {
          if (res[1].statusCode === 200) {
            this.inputContent = '';
            uni.$emit('refresh', {
              cond: 'sub',
            });
            uni.$emit('tobottom', {
              cond: 'sub',
            });
            this.$refs.uToast.show({
              title: '发表评论成功',
            });
          } else {
            console.log(res[1].data.data);
          }
        });
      } else {
        addPostComment(this.postID, this.inputContent).then((res) => {
          if (res[1].statusCode === 200) {
            this.inputContent = '';
            this.$refs.uToast.show({
              title: '发表评论成功',
            });
            uni.$emit('refresh', {
              cond: 'main',
              postID: this.postID,
            });
            uni.$emit('tobottom', {
              cond: 'main',
            });
          } else {
            console.log(res[1].data.data);
          }
        });
      }
    },
  },
};
</script>
<style scoped>
.publish-comment {
  position: fixed;
  bottom: 0rpx;
  z-index: 99999999;
  width: 100%;
  min-height: 120rpx;
  background-color: #f7f7f7;
}

.input {
  padding-left: 5rpx;
  width: 75%;
  margin-left: 20rpx;
  background-color: var(--white);
}
</style>
