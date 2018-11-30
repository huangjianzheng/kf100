<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
  import {getToken} from "@/utils/auth"
export default {
  name: 'App',
}
</script>
<style>
  .el-tooltip__popper{
    width: 50%;
  }
</style>
