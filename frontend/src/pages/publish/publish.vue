<template>
  <view>
    <view class="background">
      <view class="confirm-bar flex main-axis-end">
        <u-button @click="publishPost"
                  :custom-style="publishBtnStyle"
                  hover-class="none"
                  :disabled="content === ''">发布</u-button>
      </view>

      <u-line color="info"
              length="99%"></u-line>

      <view class="text-bar">
        <textarea v-model="content"
                  placeholder="写下你的具体求助信息..."
                  maxlength="1000"
                  fixed="true"></textarea>
      </view>

      <view class="photo-bar flex">
        <u-upload :auto-upload="false"
                  name="1"
                  :maxCount="1"
                  :max-size="20 * 1024 * 1024"
                  width="200"
                  height="200"
                  ref="uUpload"
                  :form-data="form"
                  :header="header"
                  show-progress="false"
                  onload=""></u-upload>
      </view>

      <view class="place-bar flex"
            @click="getLocation">
        <view class="icon flex main-axis-center">
          <u-image width="36rpx"
                   height="36rpx"
                   src="/static/publish/place.png"></u-image>
          <text>{{ place }}</text>
        </view>
      </view>

      <u-line color="rgba(192, 192, 192, 0.7)"
              length="99%"></u-line>

      <view class="topics-bar flex"
            @click="chooseTopics">
        <view class="icon flex main-axis-center">
          <u-image width="33rpx"
                   height="33rpx"
                   src="/static/publish/topic.png"></u-image>
          <text> {{ topicsName }} </text>
        </view>
      </view>

      <u-line color="rgba(192, 192, 192, 0.7)"
              length="99%"></u-line>

      <view class="state-bar flex"
            @click="chooseStates">
        <view class="icon flex main-axis-center">
          <u-image width="36rpx"
                   height="36rpx"
                   src="/static/publish/state.png"></u-image>
          <text> {{state}} </text>
        </view>
      </view>
      <u-select v-model="showState"
                mode="single-column"
                :list="stateList"
                @confirm="confirmStates"
                confirm-color="var(--primary-color-2)"></u-select>

      <u-line color="rgba(192, 192, 192, 0.7)"
              length="99%"></u-line>

      <u-toast ref="uToast" />
    </view>
  </view>
</template>

<script>
import { publishPost } from '../../js/api';
export default {
  data () {
    return {
      publishBtnStyle: {
        width: '85%',
        height: '50rpx',
        backgroundColor: 'var(--primary-color-1)',
        color: 'white'
      },
      uploadUrl: '',
      header: {
        token: uni.getStorageSync('token'),
        'content-type': 'multipart/form-data',
      },
      form: {
        image: this.$refs.uUpload.lists,
        id: '',
      },
      content: '',
      image:'',
      place: '选择地址',
      topics: '',
      topicsName: '选择话题',
      state: '紧急状态',
      showState: false,
      stateList: [
        {
          value: '1',
          label: '普通求助'
        },
        {
          value: '2',
          label: '略微紧急'
        },
        {
          value: '3',
          label: '十万火急'
        }
      ],
    };
  },
  onLoad: function () {
   
  },
  onShow:function(){
    let pages = getCurrentPages(); 
    let prevPage = pages[pages.length - 1];
    this.topics=prevPage.topics;
    if(this.topics==null)this.topics='';
  },
  methods: {
    getLocation () {
      const that = this;
      uni.chooseLocation({
        success: function (res) {
          if (res.name != null) {
            that.place = res.name;
          }
        },
      });
    },
    chooseTopics () {
      uni.navigateTo({
        url: './topic',
      });
      //this.topics=origin.tagID;
    },
    chooseStates () {
      this.showState = true
    },
    confirmStates (e) {
      this.state = e[0].label
    },
    publishPost () {
      if(this.state=='普通求助'||this.state=='紧急状态'){
        this.state=1;
      }else if(this.state=='略微紧急'){
        this.state=2;
      }else{
        this.state=3;
      }
      if(this.$refs.uUpload.lists.length!=0){
        this.image=this.$refs.uUpload.lists[0].url;
      }
      publishPost(uni.getStorageSync('userID'),this.content,this.image,
      this.place,this.topics,this.state).then((res)=>{
        console.log(res);
        wx.switchTab({
          url: '../index/index'
        });
      });
    },
  },
};
</script>

<style scoped>
.background {
  width: 95vw;
  height: 100vh;
  margin: auto;
}

.confirm-bar {
  width: 100%;
  height: 6%;
  margin: auto;
  font-size: 30rpx;
}

.text-bar {
  padding: 15rpx;
  margin-top: 10rpx;
  height: 25%;
  width: 100%;
  background-color: rgba(171, 171, 171, 0.1);
}

.text-bar textarea {
  width: 100%;
  height: 90%;
}

.photo-bar {
  min-height: 25%;
  margin-bottom: 20rpx;
}

.place-bar,
.topics-bar,
.state-bar {
  margin-top: 10rpx;
  min-height: 5%;
  font-size: 30rpx;
}
</style>
