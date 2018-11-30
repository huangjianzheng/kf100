'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  BASE_API: '"http://localhost:9528/api"',//http://localhost:9528/api
  UPLOAD_IMAGE_URL: '"http://localhost:9528/api/upload/image"',
  IMAGE_PATH: '"http://localhost:8080/static/image/"',
  IMAGE_MAX_SIZE: 5242880,
  IMAGE_TYPES: '["image/jpeg", "image/png", "image/bmp"]',
})
