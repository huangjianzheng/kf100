<template>
  <div class="app-container">
    <div class="title-bar">
      <el-button type="primary" icon="el-icon-plus" plain class="fr">新建资讯</el-button>
    </div>
    <div class="filter-bar">
      <span>资讯位置:</span>
      <el-select v-model="searchCondition.type">
        <el-option v-for="item in typeList" :key="item.value" :label="item.label" :value="item.value">
        </el-option>
      </el-select>
      <span>一级分类:</span>
      <el-select v-model="searchCondition.topLevelCategoryId" @change="handleTopLevelCategorySelectChange">
        <el-option v-for="item in topLevelCategoryList" :key="item.id" :label="item.name" :value="item.id">
        </el-option>
      </el-select>
      <span>二级分类:</span>
      <el-select v-model="searchCondition.secondLevelCategoryId">
        <el-option v-for="item in secondLevelCategoryList" :key="item.id" :label="item.name" :value="item.id">
        </el-option>
      </el-select>
      <span>发布时间:</span>
      <el-date-picker v-model="begin" type="date" placeholder="开始日期" value-format="timestamp"></el-date-picker>
      <el-date-picker v-model="end" type="date" placeholder="结束日期" value-format="timestamp"></el-date-picker>
      <span>状态:</span>
      <el-select v-model="searchCondition.status">
        <el-option v-for="item in statusList" :key="item.value" :label="item.label" :value="item.value">
        </el-option>
      </el-select>
      <el-input v-model="searchCondition.title" placeholder="按标题搜索" class="filter-input"/>
      <el-button style="margin-left: 20px;">搜索</el-button>
    </div>
  </div>
</template>

<script>
  import {getTopLevelCategoryList,getSecondLevelCategoryListByParentId} from "../../api/article";

  export default {
    data() {
      return {
        uploadImageUrl: process.env.UPLOAD_IMAGE_URL,
        imageTypes: process.env.IMAGE_TYPES,
        imageMaxSize: process.env.IMAGE_MAX_SIZE,
        imagePath: process.env.IMAGE_PATH,
        searchCondition: {
          type: '',
          topLevelCategoryId: '',
          secondLevelCategoryId: '',
          begin: '',
          end: '',
          status: '',
          title:'',
          current:1,
          pageSize:10
        },
        typeList: [
          {
            value: '',
            label: '全部'
          },
          {
            value: 1,
            label: '新闻'
          },
          {
            value: 2,
            label: '公告'
          }
        ],
        statusList: [
          {
            value: '',
            label: '全部'
          },
          {
            value: 1,
            label: '已发布'
          },
          {
            value: 0,
            label: '未发布'
          }
        ],
        topLevelCategoryList: [
          {
            id: '',
            name: '全部'
          }
        ],
        secondLevelCategoryList: [
          {
            id: '',
            name: '全部'
          }
        ],
      }
    },
    created() {
      this.fetchData();
    },
    methods: {
      fetchData() {
        getTopLevelCategoryList().then(response => {
          let temp = [
            {
              id: '',
              name: '全部'
            }
          ];
          this.topLevelCategoryList = temp.concat(response.data);
        });
      },
      handleTopLevelCategorySelectChange(val){
        let temp = [
          {
            id: '',
            name: '全部'
          }
        ];
        if(val === ''){
          this.secondLevelCategoryList = temp;
        }else{
          getSecondLevelCategoryListByParentId(val).then(response => {
            this.secondLevelCategoryList = temp.concat(response.data);
          });
        }
      }
    },
  }
</script>

<style scoped>
  .filter-bar {
    padding: 10px 0px 10px 0px;
  }

  .filter-bar span {
    margin-left: 20px;
  }

  .filter-bar .el-select {
    width: 130px;
  }

  .filter-input {
    margin-left: 20px;
    width: 150px;
  }

  .fr {
    float: right;
  }

  .fl {
    float: left;
  }

  .title-bar {
    border-bottom: solid 1px #e6e6e6;
    height: 50px;
  }

  .page-bar {
    padding: 10px 0px 10px 0px;
  }

  .form-upload {
    padding-left: 100px;
    padding-bottom: 20px;
  }
</style>

