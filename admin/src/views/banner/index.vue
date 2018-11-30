<template>
  <div class="app-container">
  </div>
</template>

<script>
  export default {
    data() {
      return {
        uploadImageUrl: process.env.UPLOAD_IMAGE_URL,
        imageTypes: process.env.IMAGE_TYPES,
        imageMaxSize: process.env.IMAGE_MAX_SIZE,
        imagePath: process.env.IMAGE_PATH,
      }
    },
    created() {
    },
    methods: {
    },
  }
</script>

<style scoped>

</style>

