<template>
  <view>
    <post :key="index"
          :post-info="postInfo"
          :isOpen="true"
          :isPersonHomepage="false"
          @refreshLike="refreshLike"
          @refreshCollect="refreshCollect"></post>

    <comment ref="comment"
             @refresh="loadData"
             :post-i-d="postInfo.post.id"
             v-for="item in comments"
             :key="item.comment.id"
             :comment="item.comment"
             :subcomment="item.subcomment"
             :isParentComment="true"
             @deleteComment="updateComment"></comment>

    <!-- 底部输入框 -->
    <publish-comment ref="publishComment"
                     :postID="postInfo.post.id"
                     placeholder="发表一条和善的评论..."
                     type="postComment"></publish-comment>
  </view>
</template>
<script>
import post from '../../components/post.vue'
import comment from '../../components/comment.vue';
import PublishComment from '../../components/publish-comment.vue'
export default {
  components: {
    post,
    comment,
    PublishComment
  },
  data () {
    return {
      postInfo:
      {
        avatarUrl: 'https://pic2.zhimg.com/v2-ffdb112aa665dfdc6623b3bfc2933c5f_r.jpg',
        nickName: '小冯来一口锅包肉',
        time: '2022.5.22',
        place: '南开大学泰达学院',
        content: '求蔬菜水果，已经很多天没有补充维生素了',
        hotNumber: 999,
        topics: [{ id: 0, content: '天津新一轮疫情' }],
        images:
          [
            'https://att2.citysbs.com/hangzhou/2020/02/08/20/middle_1080x720-204341_v2_10701581165821468_3d1c277d226673ef0e0c6c082f6efd1d.jpg',
          ],
      },
    };
  },


  methods: {

  },
};
</script>
<style scoped>
</style>
