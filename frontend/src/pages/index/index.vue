<template>
  <view class="background">

    <!-- 搜索栏 -->
    <view style="padding: 25rpx 15rpx 0rpx; height: 7%;">
      <u-search placeholder="上海疫情互助"
                :disabled="true"
                :show-action="false"
                :shape="square"
                @click="goSearch"></u-search>
    </view>

    <!-- 此刻与关注的tab栏 -->
    <view style="height: 7%;">
      <u-tabs id="tabs"
              @change="setIndex"
              :list="tabList"
              :current="targetIndex"
              data-index="index"
              active-color="var(--primary-color-1)"
              font-size="26"></u-tabs>
    </view>

    <swiper id="swiper"
            class="swiper"
            :current="thisIndex"
            :data-index="thisIndex"
            @change="toggle"
            circular
            style="height: 86%;">

      <!-- 此刻 -->
      <swiper-item>
        <scroll-view scroll-y
                     style="height: 100%;">

          <!-- 轮播图 -->
          <view style="padding: 15rpx 25rpx 25rpx;">
            <u-swiper :list="rotationList"
                      :effect3d="true"
                      :autoplay="true"
                      interval="3000"
                      duration="700"></u-swiper>
          </view>

          <!-- 互助帖子 -->
          <post v-for="(postInfo, index) in postInfos"
                :key="index"
                :post-info="postInfo"
                :isOpen="false"
                :isPersonHomepage="false"
                @deletePost="updateDeletePost"
                @refreshLike="refreshLike"
                @refreshCollect="refreshCollect"></post>
          <view style="height: 120rpx;"></view>

          <!-- 发布按钮 -->
          <u-icon class="publish"
                  @click="publishMoments"
                  name="plus-circle-fill"
                  color="var(--primary-color-1)"
                  size="120"></u-icon>
        </scroll-view>
      </swiper-item>

      <!-- 关注 -->
      <swiper-item>
        <scroll-view scroll-y="true"
                     style="height: 100%;">

          <!-- 默认 -->
          <view class="flex main-axis-center"
                style="margin-top: 35rpx; font-size: 30rpx"
                v-if="followPostInfos.length===0">你还没有关注任何求助帖~</view>

          <!-- 我关注的帖子 -->
          <post v-show="followPostInfos.length!==0"
                v-for="(followPostInfo, index) in followPostInfos"
                :key="index"
                :post-info="followPostInfo"
                :isOpen="false"
                :isPersonHomepage="false"
                @deletePost="updateDeletePost"
                @refreshLike="refreshLike"
                @refreshCollect="refreshCollect"></post>
          <view style="height: 120rpx;"></view>

          <u-icon class="publish"
                  @click="publishMoments"
                  name="plus-circle-fill"
                  color="var(--primary-color-1)"
                  size="120"></u-icon>

        </scroll-view>
      </swiper-item>

    </swiper>

    <!-- 底部导航栏 -->
    <u-tabbar v-model="currentBottomTab"
              :list="bottomTabList"
              bg-color="var(--gray)"
              active-color="var(--primary-color-1)"></u-tabbar>
  </view>
</template>

<script>
import { getAllPost, getAllMyFollowPost } from '../../js/api';
import post from '../../components/post.vue'
export default {
  components: {
    post,
  },
  data () {
    return {
      tabList: [
        {
          name: '此刻'
        },
        {
          name: '关注'
        }
      ],
      thisIndex: 0,
      targetIndex: 0,
      rotationList: [
        'https://tse4-mm.cn.bing.net/th/id/OIP-C.SviZKP29gTlN3HdoBYwvVgHaEK?pid=ImgDet&rs=1',
        'https://n.sinaimg.cn/sinakd2020321s/783/w950h633/20200321/37a1-ireifzh1305231.jpg',
        'https://att2.citysbs.com/hangzhou/2020/02/08/20/middle_1080x720-204341_v2_10701581165821468_3d1c277d226673ef0e0c6c082f6efd1d.jpg',
      ],
      currentBottomTab: 0,
      bottomTabList: [
        {
          iconPath: "home",
          selectedIconPath: "home-fill",
          text: '首页',
          pagePath: '/pages/index/index'
        },
        {
          iconPath: "fingerprint",
          selectedIconPath: "fingerprint",
          text: '登录',
          pagePath: '/pages/person/login'
        },
        {
          iconPath: "account",
          selectedIconPath: "account-fill",
          text: '个人',
          pagePath: '/pages/person/person',
        }
      ],
      postInfos: [],
      followPostInfos: []
    }
  },
  onLoad () {
    this.loadPost();
  },
  // mounted () {
  //   let tabBottom;
  //   uni.createSelectorQuery()
  //     .select('#tabs')
  //     .boundingClientRect((data) => {
  //       tabBottom = data.bottom;
  //     })
  //     .exec();
  //   // uni.createSelectorQuery()
  //   //   .select('#tabbar')
  //   //   .boundingClientRect((data) => {
  //   //     this.tabbarHeight = data.height;
  //   //   })
  //   //   .exec();
  //   uni.createSelectorQuery()
  //     .select('#swiper')
  //     .boundingClientRect((data) => {
  //       // this.swiperHeight += data.top - tabBottom - this.tabbarHeight;
  //       this.swiperHeight += data.top - tabBottom;
  //     })
  //     .exec();
  // },
  methods: {
    loadPost () {
      console.log("load data...");
      getAllPost().then((res) => {
        this.postInfos = res[1].data
      });
      getAllMyFollowPost(uni.getStorageSync('userID')).then((res) => {
        this.followPostInfos = res[1].data
      });

    },
    setIndex (index) {
      this.thisIndex = index;
    },
    toggle (e) {
      const index = e.detail.current;
      this.targetIndex = index;
      this.thisIndex = index;
      this.loadPost();
    },
    publishMoments () {
      uni.navigateTo({
        url: '../publish/publish',
      });
    },
    goSearch () {
      uni.navigateTo({
        url: '../index/search',
      });
    }
  }
}
</script>

<style>
.background {
  height: 100vh;
  width: 100vw;
}

.swiper {
  position: absolute;
  height: 65%;
  width: 100%;
}

.publish {
  position: fixed;
  bottom: 250rpx;
  right: 25rpx;
  z-index: 10;
  background-color: #fff;
  border-radius: 50%;
}
</style>
