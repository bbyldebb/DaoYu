<template>
  <view class="background">

    <!-- 搜索栏 -->
    <view style="padding: 25rpx 15rpx 0rpx; height: 7%;">
      <u-search placeholder="上海疫情互助"
                :clearabled="true"
                :shape="square"
                :action-style="actionStyle"></u-search>
    </view>

    <scroll-view scroll-y
                 style="height: 93%;">
      <view style="height: 17rpx;"></view>
      <!-- 互助帖子 -->
      <post v-for="(postInfo, index) in postInfos"
            :key="index"
            :post-info="postInfo"
            :isOpen="true"
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
import post from '../../components/post.vue'
export default {
  components: {
    post,
  },
  data () {
    return {
      keyword: '',
      actionStyle: {
        width: '13%',
        height: '60rpx',
        backgroundColor: 'var(--primary-color-1)',
        color: 'white',
        borderRadius: '10rpx',
        lineHeight: '60rpx',
        fontWeight: '530'
      },
      postInfos: [
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
              'https://n.sinaimg.cn/sinakd10114/140/w640h300/20200528/38ab-iufmpmn5330925.jpg',
              'https://tse4-mm.cn.bing.net/th/id/OIP-C.SviZKP29gTlN3HdoBYwvVgHaEK?pid=ImgDet&rs=1',
              'https://n.sinaimg.cn/sinakd2020321s/783/w950h633/20200321/37a1-ireifzh1305231.jpg',
              'https://att2.citysbs.com/hangzhou/2020/02/08/20/middle_1080x720-204341_v2_10701581165821468_3d1c277d226673ef0e0c6c082f6efd1d.jpg',
            ],
        },
        {
          avatarUrl: 'https://pic.qqtn.com/up/2018-4/15234396009288466.jpg',
          nickName: '石菜菜',
          time: '2022.5.22',
          place: '南开大学津南校区5公寓',
          content: '求口罩，消毒酒精！！！',
          hotNumber: 37,
          solved: true
        },
        {
          avatarUrl: 'https://tse1-mm.cn.bing.net/th/id/R-C.c8b570db202fcc778cdb850f29c4f04d?rik=g83VNYgzYwmGvw&riu=http%3a%2f%2fpic.22520.cn%2fup%2f200820%2f1597902299377345.jpg&ehk=e9Fn9at%2fOiXLZThmm5YmYmT1QfbzsWXFTIj6%2fu3OVAo%3d&risl=&pid=ImgRaw&r=0&sres=1&sresct=1',
          nickName: '久久子',
          time: '2022.5.22',
          place: '上海交通大学',
          content: '我有多余的零食，可以分享，需要的自取',
          hotNumber: 4,
          topics: [{ id: 0, content: '上海封城' }],

        },
        {
          avatarUrl: 'https://tse1-mm.cn.bing.net/th/id/R-C.026a1dbac43f5a01457ac3ffa91cf082?rik=9KWgmnt21bZSEg&riu=http%3a%2f%2fn.sinaimg.cn%2fsinakd20200812ac%2f283%2fw640h443%2f20200812%2fab98-ixreehp2745953.jpg&ehk=Kg82UJp6Bvw3rYaSYuE87Qm3hujKaluMZhu8oYN6SMc%3d&risl=&pid=ImgRaw&r=0&sres=1&sresct=1',
          nickName: '柳智敏的狗',
          time: '2022.5.22',
          place: '',
          content: '这里有一车的口罩、酒精资源，求送往疫区途径',
          hotNumber: 102
        },
      ]
    }
  },
  onLoad () {
  },
  methods: {
  }
}
</script>

<style>
.background {
  height: 100vh;
  width: 100vw;
}
</style>
