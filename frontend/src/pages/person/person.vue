<template>
  <view class="background">
    <view>
      <view class="card flex"
            id="card">
        <view class="cardIdentify flex flex-col main-axis-center cross-axis-center">
          <view>
            <u-avatar :src="avatarUrl"
                      :size="150"></u-avatar>
          </view>
        </view>
        <view style="width: 65%"
              class="cardContext flex flex-col">
          <view class="flex"
                style="height: 45%; font-size: 33rpx; padding-top: 20rpx;">
            {{ nickName }}
          </view>
          <view class="flex"
                style="height: 55%; color: gray;">
            {{ introduction }}
            <u-icon name="edit-pen-fill"
                    size="28"
                    color="rgba(153,153,153, 1)"
                    @click="editInfo"></u-icon>
          </view>
        </view>
      </view>

      <view class="scoreplat"
            id="scoreplat"
            :style="{ top: `${cardHeight}px` }">
        <view class="score flex main-axis-between">
          <view style="font-weight: bold; margin-left: 7px">道雨</view>
          <view style="margin-right: 7px; color: gray">青山一道同风雨</view>
        </view>
        <view class="flex flex-col">
          <view class="imgUp flex">
            <view class="blankImg flex flex-col main-axis-center">
              <view>
                <u-icon class="icon-item"
                        name="/static/person/person/1.png"
                        size="65"></u-icon>
              </view>
              <view style="font-weight: bold; color: gray; font-size: 26rpx;">互助平台</view>
            </view>
            <view class="blankImg flex flex-col main-axis-center">
              <view>
                <u-icon class="icon-item"
                        name="/static/person/person/2.png"
                        size="65"></u-icon>
              </view>
              <view style="font-weight: bold; color: gray; font-size: 26rpx;">新冠疫情</view>
            </view>
            <view class="fullImg flex flex-col main-axis-center">
              <view>
                <u-icon class="icon-item"
                        name="/static/person/person/3.png"
                        size="65"></u-icon>
              </view>
              <view style="font-weight: bold; color: gray; font-size: 26rpx;">众志成城</view>
            </view>
          </view>
          <view class="imgDown flex">
            <view class="fullImg flex flex-col main-axis-center">
              <view>
                <u-icon class="icon-item"
                        name="/static/person/person/4.png"
                        size="65"></u-icon>
              </view>
              <view style="font-weight: bold; color: gray; font-size: 26rpx;">邻里之间</view>
            </view>
            <view class="fullImg flex flex-col main-axis-center">
              <view>
                <u-icon class="icon-item"
                        name="/static/person/person/5.png"
                        size="65"></u-icon>
              </view>
              <view style="font-weight: bold; color: gray; font-size: 26rpx;">人间大爱</view>
            </view>
            <view class="fullImg flex flex-col main-axis-center">
              <view>
                <u-icon class="icon-item"
                        name="/static/person/person/6.png"
                        size="65"></u-icon>
              </view>
              <view style="font-weight: bold; color: gray; font-size: 26rpx;">强大合力</view>
            </view>
          </view>
        </view>
      </view>

      <view class="tabSwiper"
            :style="{
                    top: `${swiperBarTop}px`,
                }">
        <view>
          <u-tabs @change="setIndex"
                  :list="list"
                  :current="target"
                  data-index="index"
                  active-color="var(--primary-color-1)"
                  font-size="26"
                  id="tabs"></u-tabs>
        </view>
        <swiper id="swiper"
                class="swiper"
                :current="thisindex"
                :data-index="thisindex"
                @change="toggle"
                :style="{
                        height: `${swiperHeight}px`,
                        marginBottom: `${tabbarHeight}px`,
                    }"
                circular>
          <swiper-item>
            <scroll-view scroll-y="true"
                         class="scroll-box">
              <post v-for="(postInfo, index) in userPosts"
                    :key="index"
                     :post-info="postInfo"
                    :isOpen="true"
                    :isPersonHomepage="false"
                    @deletePost="updateDeletePost"
                    @refreshLike="refreshLike"
                    @refreshCollect="refreshCollect"></post>
            </scroll-view>
          </swiper-item>
          <swiper-item>
            <scroll-view scroll-y="true"
                         class="scroll-box">
              <post v-for="(postInfo, index) in followPosts"
                    :key="index"
                    :postInfo="postInfo"
                    :isOpen="true"
                    @deletePost="updateDeletePost"></post>
            </scroll-view>
          </swiper-item>
        </swiper>
      </view>
    </view>

    <u-tabbar id="tabbar"
              class="tabbar"
              v-model="currentBottomTab"
              :list="bottomTabList"
              bg-color="var(--gray)"
              active-color="var(--primary-color-1)"></u-tabbar>

  </view>
</template>

<script>
import { getAllMyPost,getAllMyFollowPost,getUserInfo } from '../../js/api';
import post from '../../components/post.vue'
export default {
   components: {
    post,
  },
  data () {
    return {
      swiperHeight: 0,
      tabbarHeight: 0,
      swiperBarTop: 0,
      cardHeight: 0,
      id: 123456,
      avatarUrl: '',
      nickName: '',
      introduction: '',
      customStyle: {
        width: '500rpx',
        height: '56rpx',
        marginTop: '20rpx',
      },
      list: [
        {
          name: '我的求助',
        },
        {
          name: '我的关注',
        }
      ],
      // 当前样式
      target: 0,
      // 当前item位置
      thisindex: 0,
      currentBottomTab: 2,
      collections: null,
      userPosts: [],
      followPosts:[],
      bottomTabList: [
        {
          iconPath: "home",
          selectedIconPath: "home-fill",
          text: '首页',
          midButton: true,
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
      show_message: '授权登录',
    };
  },
  onLoad () {
    // getMyInformation().then((res) => {
    //   const data = res[1].data.data;
    //   this.scores = data.points;
    //   this.vitality = data.level;
    //   this.fans = data.fans;
    //   this.followers = data.followers;
    //   this.likes = data.likes;
    //   if (data.introduction === '') {
    //     this.introduction = '这个人很懒，什么也没留下~';
    //   } else this.introduction = data.introduction;
    //   this.id = data.id;
    //   if (data.avatarUrl !== null) {
    //     this.avatarUrl = data.avatarUrl;
    //     const Base64 = require('js-base64').Base64;
    //     this.nickName = Base64.decode(data.nickName);
    //   }
    //   getUserPost(1, 10, this.id).then((res) => {
    //     this.userPosts = res[1].data.data.posts;
    //   });
    //   getPostFeedbacks(1, 10, this.id).then((res) => {
    //     this.feedbacks = res[1].data.data.feedbacks;
    //   });
    // });
    if (uni.getStorageSync('userId') === '') {
      this.show_message = '授权登录';
    } else {
      this.show_message = '编辑资料';
    }
    this.id=uni.getStorageSync('userID')
    getUserInfo(this.id).then((res)=>{
      console.log(res);
      this.avatarUrl=res[1].data.avatarImg;
      this.nickName=res[1].data.nickName;
      this.introduction=res[1].data.introduction;
    });
    getAllMyPost(this.id).then((res)=>{
      this.userPosts=res[1].data;
      console.log(this.userPosts);
    });
    getAllMyFollowPost(this.id).then((res)=>{
      this.followPosts=res[1].data;
      console.log(this.followPosts);
    });
  },
  mounted () {
    let cardBottom;
    uni.createSelectorQuery()
      .select('#card')
      .boundingClientRect((data) => {
        cardBottom = data.bottom;
        this.cardHeight = data.height;
      })
      .exec();
    let tabHeight;
    uni.createSelectorQuery()
      .select('#tabs')
      .boundingClientRect((data) => {
        tabHeight = data.height;
      })
      .exec();
    let tabTop;
    uni.createSelectorQuery()
      .select('#tabbar')
      .boundingClientRect((data) => {
        this.tabbarHeight = data.height;
        tabTop = data.top;
        this.swiperHeight = tabTop - cardBottom - tabHeight;
      })
      .exec();
    let scorePlatHeight;
    uni.createSelectorQuery()
      .select('#scoreplat')
      .boundingClientRect((data) => {
        scorePlatHeight = data.height;
        this.swiperBarTop = scorePlatHeight + this.cardHeight;
      })
      .exec();
  },
  methods: {
    editInfo () {
      uni.navigateTo({
        url: './edit-profile',
      });
      // if (uni.getStorageSync('userId') === '') {
      //   uni.navigateTo({
      //     url: '../index/login',
      //     fail: (res) => {
      //       console.log(res);
      //     },
      //   });
      // } else {
      //   uni.navigateTo({
      //     url: './edit-profile',
      //   });
      // }
    },
    // 切换触发的事件
    toggle (e) {
      const index = e.detail.current;
      this.target = index;
    },
    // 点击nav控制下面的展示
    setIndex (index) {
      this.thisindex = index;
    }
  },
};
</script>

<style scoped>
.background {
  min-height: 100vh;
}
.card {
  height: 20%;
  margin: 7px;
  padding: 12px;
  border-radius: 6px;
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.3);
  position: fixed;
  background-color: white;
  z-index: 1;
  right: 0px;
  left: 0px;
}
.cardContext {
  width: 65%;
  height: 100%;
}
.cardIdentify {
  justify-content: space-evenly;
  width: 35%;
  height: 100%;
}
.score {
  padding: 10px 6px 12px;
  margin: 7px 7px 0px;
  border-bottom: 3rpx solid rgb(153, 153, 153, 0.3);
}
.scoreplat {
  position: fixed;
  width: 100%;
}
.blankImg {
  width: 88px;
  height: 82px;
  border: 5rpx solid rgb(26, 173, 25, 0.2);
  border-radius: 16px;
}
.fullImg {
  width: 88px;
  height: 82px;
  border-radius: 16px;
  background-color: rgba(26, 173, 25, 0.2);
}
.imgUp {
  justify-content: space-evenly;
  width: 100%;
  margin-bottom: 10rpx;
  margin-top: 20rpx;
}
.imgDown {
  justify-content: space-evenly;
  width: 100%;
  margin-bottom: 20rpx;
  margin-top: 10rpx;
}
.scroll {
  height: 400rpx;
  width: 100%;
}
.scroll-view {
  background-color: var(--white);
  width: 100%;
}
.swiper-item {
  width: 100%;
  overflow: hidden;
  text-align: center;
  line-height: 300rpx;
  overflow-y: scroll;
  width: 99.5%;
  height: 99%;
  box-sizing: border-box;
  padding: 1rpx;
}
.swiper {
  width: 100%;
  background-color: white;
}
.scroll-box {
  height: 100%;
}
.tabbar {
  position: fixed;
  bottom: 0;
}
.tabSwiper {
  position: relative;
}
</style>
