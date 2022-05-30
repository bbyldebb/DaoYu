<template>
  <view class="content">
    <view class="title">
      <text>基本信息</text>
    </view>

    <u-form :model="userInfo"
            ref="uForm">
      <u-form-item label="性别">
        <u-input v-model="userInfo.sex"
                 placeholder="点击选择您的性别信息"
                 type="select"
                 @click="sexShow = true" />
        <u-select v-model="sexShow"
                  mode="single-column"
                  :list="sexList"
                  @confirm="chooseSex"></u-select>
      </u-form-item>

      <u-form-item label="生日">
        <u-input v-model="userInfo.birth"
                 placeholder="点击选择您的生日信息"
                 type="select"
                 @click="birthShow = true" />
        <u-calendar v-model="birthShow"
                    mode="date"
                    @change="chooseBirth"></u-calendar>
      </u-form-item>

      <u-form-item label="地址"
                   prop="place">
        <u-input v-model="userInfo.place"
                 placeholder="请输入地址信息,不超过64个字符" />
      </u-form-item>
      <u-form-item label="简介"
                   prop="intro">
        <u-input v-model="userInfo.introduction"
                 placeholder="请输入个人简介,不超过30个字符" />
      </u-form-item>
    </u-form>
    <u-button :custom-style="customStyle"
              @click="submit"
              hover-class="none">提交</u-button>
  </view>
</template>

<script>
export default {
  data () {
    return {
      sexShow: false,
      birthShow: false,
      userInfo: {},
      sexList: [
        {
          value: 'man',
          label: '男',
        },
        {
          value: 'woman',
          label: '女',
        },
      ],
      rules: {
        place: [
          {
            max: 64,
            message: '地址不超过64个字符',
            trigger: 'change',
          },
        ],
        intro: [
          {
            max: 30,
            message: '简介不超过30个字符',
            trigger: 'change',
          },
        ],
      },
      customStyle: {
        width: '100%',
        height: '80%',
        backgroundColor: 'var(--primary-color-1)',
        color: 'white',
        fontWeight: 'bold',
        marginTop: '35rpx'
      }
    };
  },
  onLoad: function () {
    getMyInformation().then((res) => {
      this.userInfo = res[1].data.data;
      if (this.userInfo.birth === null) {
        this.userInfo.birth = '';
      }
    });
  },
  onReady () {
    this.$refs.uForm.setRules(this.rules);
  },
  methods: {
    chooseSex (e) {
      this.userInfo.sex = e[0].label;
    },
    chooseBirth (e) {
      this.userInfo.birth = e.year + '-' + e.month + '-' + e.day;
    },
    submit () {
      updateUserInformation(
        this.userInfo.place,
        this.userInfo.introduction,
        this.userInfo.sex,
        this.userInfo.birth
      ).then((res) => {
        console.log('complete submit');
        uni.reLaunch({
          url: '../mine/mine',
        });
      });
    },
  },
};
</script>

<style>
page {
  width: 100%;
  height: 100%;
}

.content {
  width: 95%;
  height: 100%;
  margin: auto;
  display: flex;
  flex-direction: column;
}

.title {
  height: 7%;
  display: flex;
  align-items: center;
  color: gray;
  font-weight: bold;
}

.u-form-item {
  height: 15%;
}

.photo {
  display: flex;
  width: 100%;
  height: 100%;
  justify-content: flex-end;
}
</style>
s