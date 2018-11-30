<template>
  <div class="dashboard-container">
    <div class="dashboard-text">欢迎{{name}}，使用康复100-后台管理系统!</div>
  </div>
</template>

<script>
  import {mapGetters} from 'vuex'

  export default {
    name: 'dashboard',
    data() {
      return {
        userRoles: ["expert", "authenticate", "ordinaryUser"],
        isAccessApi: false
      }
    },
    computed: {
      ...mapGetters([
        'name',
        'roles'
      ])
    },
    created() {
      for (let i = 0; i < this.roles.length; i++) {
        let role = this.roles[i];
        if(this.userRoles.indexOf(role)!=-1){
          this.isAccessApi = true;
          break;
        }
      }
    }
  }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .dashboard {
    &-container {
      margin: 30px;
    }
    &-text {
      font-size: 30px;
      line-height: 46px;
    }
  }
</style>
