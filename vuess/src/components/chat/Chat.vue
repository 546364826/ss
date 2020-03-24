<!--
 * @功能描述: 
 * @作者: 郑文杰
 * @Date: 2019-10-09 15:24:04
 * @最新修改内容: 
 * @LastEditTime: 2020-03-14 17:08:52
 -->
<template>
  <div>
    <el-tabs v-model="activeName" @tab-click="handleClick" style="margin-top: 10px">
      <el-tab-pane label="系统通知" name="notification"><nf></nf></el-tab-pane>
      <el-tab-pane label="好友聊天" name="chat"><fc></fc></el-tab-pane>
    </el-tabs>
  </div>
</template>
<script>
  import FriendChat from '@/components/chat/FriendChat'
  import Notification from '@/components/chat/Notification'
  export default {
    data() {
      return {
        activeName: 'notification'
      };
    },
    methods: {
      handleClick(tab, event) {
      }
    },
    components:{
      'fc':FriendChat,
      'nf':Notification
    }
  };
</script>
