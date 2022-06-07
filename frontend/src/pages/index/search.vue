<template>
  <view class="background">
    <u-toast ref="uToast" />
    <!-- 搜索栏 -->
    <view class="search-bar flex main-axis-between">
      <u-input class="input"
               v-model="keyword"
               :placeholder="placeholder"
               :maxlength="1000"
               :border="false"></u-input>

      <view class="confirm-btn flex cross-axis-center">
        <u-button :custom-style="customStyle"
                  hover-class="none"
                  @click="search()">搜索</u-button>
      </view>
    </view>

    <scroll-view scroll-y
                 style="
                  height:
                  93%;">
      <view style="height: 17rpx;"></view>

      <view class="flex main-axis-center"
            style="margin-top: 35rpx; font-size: 30rpx"
            v-if="resultPostInfos.length===0">没有搜索到相关的求助帖子~</view>

      <!-- 互助帖子 -->
      <post v-for="(postInfo, index) in resultPostInfos"
            :key="index"
            :post-info="postInfo"
            :isOpen="false"
            :isPersonHomepage="false"
            @deletePost="updateDeletePost"
            @refreshLike="refreshLike"
            @refreshCollect="refreshCollect"></post>
      <view style="height: 20rpx;"></view>
    </scroll-view>
  </view>
</template>

</style>
<script>
import { searchPost } from '../../js/api';
import post from '../../components/post.vue'
export default {
  components: {
    post,
  },
  data () {
    return {
      keyword: '',
      placeholder: "按关键字搜索求助帖子...",
      customStyle: {
        width: '100rpx',
        height: '65rpx',
        backgroundColor: 'var(--primary-color-1)',
        color: 'white',
        borderRadius: '10rpx',
        lineHeight: '60rpx',
        fontWeight: '530'
      },
      resultPostInfos: []
    }
  },
  onLoad () {
  },
  methods: {
    search () {
      if (this.keyword != '') {
        searchPost(this.keyword).then(res => {
          this.resultPostInfos = res[1].data;
          if (this.resultPostInfos.length === 0) {
            this.$refs.uToast.show({
              title: '没有搜到相关的求助',
              type: 'error'
            });
          }
          else {
            this.$refs.uToast.show({
              title: '搜索成功',
              type: 'success'
            });
          }
        })
      }
      else {
        this.$refs.uToast.show({
          title: '请输入搜索关键字',
          type: 'info'
        });
      }
    }
  }
}
</script>

<style>
.background {
  height: 100vh;
  width: 100vw;
}

.search-bar {
  width: 97%;
  height: 7%;
  margin: auto;
  background-color: #f7f7f7;
}

.input {
  padding-left: 5rpx;
  width: 80%;
  margin: auto;
  margin-left: 20rpx;
  background-color: var(--gray);
}
</style>
