<template>
  <view class="background">

    <!-- 搜索栏 -->
    <view style="padding: 25rpx 15rpx 0rpx; height: 7%;">
      <u-search placeholder="上海疫情互助"
                v-model="keyword"
                :clearabled="true"></u-search>
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
            SwipingClass='scrollView'
            style="height: 86%;">

      <!-- 此刻 -->
      <swiper-item class="scrollView">
        <scroll-view scroll-y
                     style="height: 100%;">

          <!-- 轮播图 -->
          <view style="padding: 15rpx 25rpx 15rpx;">
            <u-swiper :list="rotationList"
                      :effect3d="true"
                      :autoplay="true"
                      interval="3000"
                      duration="700"></u-swiper>
          </view>

          <!-- 互助帖子 -->
          <!-- <view v-for="img in rotationList">
            <u-image width="70%"
                     height="300rpx"
                     :src="img">
            </u-image>
            <view style="height: 15rpx;"></view>
          </view> -->
          <!-- <post @refresh="addComment(index)"
                v-for="(postInfo, index) in postInfos"
                :key="index"
                :post-info="postInfo"
                :isOpen="true"
                :isPersonHomepage="false"
                @deletePost="updateDeletePost"
                @refreshLike="refreshLike"
                @refreshCollect="refreshCollect"></post> -->

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

</style>
<script>
export default {
  data () {
    return {
      keyword: '',
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
      currentBottomTab: 1,
      bottomTabList: [{
        iconPath: "email",
        selectedIconPath: "email-fill",
        text: '消息',
        count: 0,
        isDot: false,
        pagePath: ''
      },
      {
        iconPath: "home",
        selectedIconPath: "home-fill",
        text: '首页',
        midButton: true,
        pagePath: ''
      },
      {
        iconPath: "account",
        selectedIconPath: "account-fill",
        text: '个人',
        pagePath: ''
      }
      ]

    }
  },
  onLoad () {
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
    setIndex (index) {
      this.thisIndex = index;
    },
    toggle (e) {
      const index = e.detail.current;
      this.targetIndex = index;
      this.thisIndex = index;
    },
    publishMoments () {
      uni.navigateTo({
        url: '../publish/publish',
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
