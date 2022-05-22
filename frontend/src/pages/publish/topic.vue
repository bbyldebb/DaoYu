<template>
  <scroll-view class="background flex">
    <view class="new-bar flex">
      <view class="href flex main-axis-center">
        <text>#</text>
      </view>
      <view class="new-topic">
        <u-input maxlength="10"
                 placeholder="你的话题/搜索话题(不超过10个字)"
                 v-model="filterText"></u-input>
      </view>
      <view class="confirm-btn flex cross-axis-center">
        <u-button :custom-style="customStyle"
                  hover-class="none"
                  @click="confirm">确定</u-button>
      </view>
    </view>

    <view class="title-bar flex">
      <text>热门话题</text>
    </view>

    <scroll-view class="all-topic"
                 scroll-y="true">
      <!--置顶话题 只选三个固定不变-->
      <view class="hot-bar"
            v-for="item in topics.slice(0, 3)"
            :key="item">
        <view class="hot-topic flex cross-axis-center main-axis-between"
              @click="chooseTopic(item.id)">
          <view class="flex">
            <text>#{{ item.topic }}</text>
            <u-image width="45rpx"
                     height="45rpx"
                     src="/static/publish/hot.png"></u-image>
          </view>
          <view v-show="item.chosen == true"
                class="selection">
            <u-image width="37rpx"
                     height="37rpx"
                     src="/static/publish/choose.png"></u-image>
          </view>
        </view>

        <view class="line"></view>
      </view>

      <!--热门话题 除开前三个固定话题之外的话题-->
      <view class="flex main-axis-center cross-axis-center">
        <text v-if="filterList.length == 0">
          没有搜到相关话题，点击确定按钮创建新的话题
        </text>
      </view>

      <view class="hot-bar"
            v-for="item in filterList"
            :key="item">
        <view class="hot-topic flex cross-axis-center main-axis-between"
              @click="chooseTopic(item.id)">
          <text>#{{ item.topic }}</text>
          <view v-show="item.chosen == true"
                class="selection">
            <u-image width="37rpx"
                     height="37rpx"
                     src="/static/publish/choose.png"></u-image>
          </view>
        </view>

        <view class="line"></view>
      </view>
    </scroll-view>
    <u-toast ref="uToast" />
  </scroll-view>
</template>

<script>
export default {
  data () {
    return {
      filterText: '',
      customStyle: {
        width: '70%',
        height: '45rpx',
        backgroundColor: 'var(--primary-color-1)',
        color: 'white',
      },
      topics: [
        {
          topic: '天津疫情',
          chosen: false,
        },
        {
          topic: '上海疫情抢菜',
          chosen: false,
        },
        {
          topic: '北京疫情发布',
          chosen: false,
        },
        {
          topic: '华人在乌克兰',
          chosen: false,
        },
        {
          topic: '阿斯加尔德地震',
          chosen: false,
        },
      ],
    };
  },
  onLoad (options) {
    getTopics().then((res) => {
      if (res[1].statusCode === 200) {
        this.topics = res[1].data.data.topics;
        this.topics = this.topics.map((element) => {
          return { ...element, chosen: false };
        });
      } else {
        console.log(res);
      }
      this.topics.filter((item) => {
        if (item.topic === options.newTopic) {
          item.chosen = true;
        }
        return item;
      });
    });
  },
  computed: {
    filterList () {
      let arr = [];
      arr = this.topics.slice(3);
      if (this.filterText) {
        arr = this.topics.filter((item) => item.topic.includes(this.filterText));
      }
      return arr;
    },
  },
  methods: {
    chooseTopic (topicID) {
      this.topics.filter((item) => {
        if (item.id === topicID) {
          item.chosen = !item.chosen;
        }
        return item;
      });
    },
    confirm () {
      let referTopics = [];
      referTopics = this.topics.filter((item) => item.chosen);
      if (referTopics.length !== 0) {
        uni.$emit('referTopics', referTopics);
        uni.navigateBack({
          delta: 1,
        });
      } else if (this.filterText !== '') {
        referTopics = this.filterList.filter((item) => item.topic === this.filterText);
        if (referTopics.length === 0) {
          const newTopic = this.filterText;
          addTopic(newTopic);
          this.$refs.uToast.show({
            title: '话题创建成功',
            type: 'success',
          });
          setTimeout(() => {
            uni.redirectTo({
              url: `../posts/topics?newTopic=${newTopic}`,
            });
          }, 500);
          this.topics.filter((item) => {
            if (item.topic === newTopic) {
              item.chosen = true;
            }
            return item;
          });
        } else {
          uni.$emit('referTopics', referTopics);
          uni.navigateBack({
            delta: 1,
          });
        }
      }
    },
  },
};
</script>

<style scoped>
.background {
  width: 100vw;
  height: 100vh;
}

.new-bar {
  width: 95%;
  height: 8%;
  margin: auto;
}

.new-bar .href {
  width: 5%;
  font-size: 36rpx;
}

.new-bar .new-topic {
  width: 75%;
}

.new-bar .confirm-btn {
  width: 10%;
}

.all-topic {
  height: 87%;
}

.title-bar {
  width: 100%;
  height: 5%;
  background-color: rgba(192, 192, 192, 0.2);
}

.title-bar text {
  padding-left: 30rpx;
}

.hot-topic {
  width: 93%;
  height: 100rpx;
  margin: auto;
}

.hot-bar text:nth-child(1) {
  min-width: 85%;
  padding-left: 20rpx;
}

.line {
  width: 100%;
  height: 0.3rpx;
  background-color: rgba(192, 192, 192, 0.3);
}
</style>
