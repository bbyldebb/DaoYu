<template>
  <view class="publish-comment flex main-axis-between">
    <u-input class="input"
             :placeholder="placeholder"
             :adjust-position="false"
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
                @click="submitComment()"
                :disabled="inputContent === ''">提交</u-button>
    </view>
    <u-toast ref="uToast" />
  </view>
</template>
<script>
import { addComment } from '../js/api'
export default {
  props: {
    postID: {
      type: Number,
      required: true,
    },
    placeholder: {
      type: String,
    }
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
      inputContent: '',
    };
  },
  methods: {
    inputChange (e) {
      this.inputContent = e;
    },
    submitComment () {
      addComment(uni.getStorageSync('userID'), this.postID, this.inputContent).then((res) => {
        if (res[1].statusCode === 200) {
          this.inputContent = '';
          this.$refs.uToast.show({
            title: '发表评论成功',
            type: 'success'
          });
          this.$emit('refreshDetail');
          this.$emit('toBottom');
        } else {
          console.log(res);
        }
      });
    },
  },
};
</script>
<style scoped>
.publish-comment {
  position: fixed;
  bottom: 0rpx;
  z-index: 100;
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
