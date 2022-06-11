<template>
  <view>
    <post :key="index"
          :post-info="postDetail"
          :isOpen="true"
          :isPersonHomepage="false"
          @modifyProcess="modifyProcess"
          @refreshCollect="refreshCollect"></post>

    <comment ref="comment"
             v-for="item in comments"
             :key="item.commentID"
             :comment="item"></comment>
    <view style="height: 130rpx;"></view>
    <view id="toBottom"></view>

    <u-modal v-model="showProcess"
             content="求助帖是否已经得到解决？"
             title="修改求助帖状态"
             :show-cancel-button="true"
             :mask-close-able="true"
             confirm-text="已解决"
             @confirm="setPostStatusZero"></u-modal>

    <u-toast ref="uToast" />

    <!-- 底部输入框 -->
    <publish-comment ref="publishComment"
                     @refreshDetail="loadData()"
                     @toBottom="toBottom()"
                     :postID="postDetail.post.postID"
                     placeholder="发表一条和善的评论..."></publish-comment>
  </view>
</template>
<script>
import post from '../../components/post.vue'
import comment from '../../components/comment.vue';
import PublishComment from '../../components/publish-comment.vue'
import { getPostDetailWithComments, setPostStatusZero } from '../../js/api'
export default {
  components: {
    post,
    comment,
    PublishComment
  },
  data () {
    return {
      postDetail: {},
      comments: {},
      postID: '',
      showProcess: false
    };
  },
  onLoad (options) {
    this.postID = options.postID;
    this.loadData();
  },
  methods: {
    loadData () {
      getPostDetailWithComments(this.postID).then(res => {
        this.postDetail = res[1].data;
        this.comments = this.postDetail.comments;
      });
    },
    toBottom () {
      setTimeout(() => {
        uni.pageScrollTo({ selector: '#toBottom', duration: 500 });
      }, 500);
    },
    modifyProcess () {
      this.showProcess = true;
    },
    setPostStatusZero () {
      setPostStatusZero(this.postID).then(res => {
        if (res[1].statusCode == 200) {
          this.$refs.uToast.show({
            title: '求助成功',
            type: 'success'
          });
          this.loadData();
        }
      })
    }
  },
};
</script>
<style scoped>
</style>
