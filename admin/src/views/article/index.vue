<template>
  <div class="app-container">
    <div class="title-bar">
      <el-button type="primary" icon="el-icon-plus" plain class="fr" @click="newArticle_click">新建资讯</el-button>
    </div>
    <div class="filter-bar">
      <span>资讯位置:</span>
      <el-select v-model="searchCondition.type">
        <el-option v-for="item in typeList" :key="item.value" :label="item.label" :value="item.value">
        </el-option>
      </el-select>
      <span>一级分类:</span>
      <el-select v-model="searchCondition.topLevelCategoryId" @change="handleTopLevelCategorySelectChange(1,$event)">
        <el-option v-for="item in topLevelCategoryList" :key="item.id" :label="item.name" :value="item.id">
        </el-option>
      </el-select>
      <span>二级分类:</span>
      <el-select v-model="searchCondition.secondLevelCategoryId">
        <el-option v-for="item in secondLevelCategoryList" :key="item.id" :label="item.name" :value="item.id">
        </el-option>
      </el-select>
      <span>发布时间:</span>
      <el-date-picker v-model="searchCondition.begin" type="date" placeholder="开始日期"
                      value-format="timestamp"></el-date-picker>
      <el-date-picker v-model="searchCondition.end" type="date" placeholder="结束日期"
                      value-format="timestamp"></el-date-picker>
      <span>状态:</span>
      <el-select v-model="searchCondition.status">
        <el-option v-for="item in statusList" :key="item.value" :label="item.label" :value="item.value">
        </el-option>
      </el-select>
      <el-input v-model="searchCondition.title" placeholder="按标题搜索" class="filter-input"/>
      <el-button style="margin-left: 20px;" @click="searchArticleList">搜索</el-button>
    </div>
    <el-table :data="articleList" style="width: 100%" border fit max-height="700">
      <el-table-column prop="id" label="编号" align="center">
      </el-table-column>
      <el-table-column prop="title" label="标题" align="center">
      </el-table-column>
      <el-table-column prop="type" label="资讯位置" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.type === 1">新闻</span>
          <span v-if="scope.row.type === 2">公告</span>
        </template>
      </el-table-column>
      <el-table-column prop="publisherName" label="发布人" align="center">
      </el-table-column>
      <el-table-column prop="topLevelCategoryName" label="一级分类" align="center">
      </el-table-column>
      <el-table-column prop="secondLevelCategoryName" label="二级分类" align="center">
      </el-table-column>
      <el-table-column prop="createTime" label="创建时间" align="center">
      </el-table-column>
      <el-table-column prop="status" label="状态" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.status === 0">未发布</span>
          <span v-if="scope.row.status === 1">已发布</span>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" align="center">
        <template slot-scope="scope">
          <el-button type="text" size="small" v-if="scope.row.status === 0" @click="publishArticle(scope.row)">
            发布
          </el-button>
          <el-button type="text" size="small" v-if="scope.row.status === 1" @click="revokeArticle(scope.row)">
            撤回
          </el-button>
          <el-button type="text" size="small" @click="updateArticle_click(scope.row)">
            修改
          </el-button>
          <el-button type="text" size="small" @click="deleteArticleById(scope.row.id)">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="page-bar fr">
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                     :current-page="searchCondition.current" :page-sizes="[5, 10, 20, 30]"
                     :page-size="searchCondition.pageSize" layout="total, sizes, prev, pager, next, jumper"
                     :total="total">
      </el-pagination>
    </div>
    <el-dialog title="新增/修改资讯" :visible.sync="dialogFormVisible" :before-close="handleDialogClose" top="5vh"
               :close-on-click-modal="false">
      <el-form ref="articleForm" :model="articleForm" :rules="rules" label-width="100px">
        <el-form-item label="文章标题：" prop="baseInfo">
          <el-input v-model="articleForm.title" placeholder="请输入文章标题"/>
        </el-form-item>
        <el-form-item label="文章简介：" prop="introduction">
          <el-input v-model="articleForm.introduction" placeholder="请输入文章简介"/>
        </el-form-item>
        <el-form-item label="文章位置：" prop="type">
          <el-select v-model="articleForm.type" placeholder="请选择文章位置">
            <el-option v-for="item in typeList_1" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="发布人：" prop="publisherName">
          <el-input v-model="articleForm.publisherName" placeholder="请输入发布人姓名"/>
        </el-form-item>
        <el-form-item label="资讯分类：" prop="topLevelCategoryId">
          <el-select v-model="articleForm.topLevelCategoryId" @change="handleTopLevelCategorySelectChange(2,$event)">
            <el-option v-for="item in topLevelCategoryList_1" :key="item.id" :label="item.name" :value="item.id">
            </el-option>
          </el-select>
          <el-select v-model="articleForm.secondLevelCategoryId">
            <el-option v-for="item in secondLevelCategoryList_1" :key="item.id" :label="item.name" :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="正文：" prop="content">
          <editor :html="articleForm.content" @change="handleTextChange" ref="editor"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="handleDialogClose">取 消</el-button>
        <el-button type="primary" @click="submitForm" :loading="articleFormLoading">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {
    deleteArticleById,
    getArticleList,
    getSecondLevelCategoryListByParentId,
    getTopLevelCategoryList,
    newArticle,
    publish,
    revoke,
    updateArticle
  } from "../../api/article";
  import Editor from "@/components/Editor";

  export default {
    components: {
      Editor
    },
    data() {
      const validateCategoryId = (rule, value, callback) => {
        if (this.articleForm.topLevelCategoryId === '') {
          callback(new Error('请选择一级分类'));
        } else if (this.articleForm.secondLevelCategoryId === '') {
          callback(new Error('请选择二级分类'));
        } else {
          callback();
        }
      };
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
          title: '',
          current: 1,
          pageSize: 10
        },
        total: 0,
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
        typeList_1: [
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
        topLevelCategoryList_1: [],
        secondLevelCategoryList: [
          {
            id: '',
            name: '全部'
          }
        ],
        secondLevelCategoryList_1: [],
        articleList: [],
        dialogFormVisible: false,
        articleForm: {
          title: '',
          id: '',
          topLevelCategoryId: '',
          secondLevelCategoryId: '',
          topLevelCategoryName: '',
          secondLevelCategoryName: '',
          type: '',
          publisherName: '',
          content: '',
          introduction: ''
        },
        rules: {
          title: {required: true, message: '请输入资讯标题', trigger: 'blur'},
          topLevelCategoryId: {trigger: 'change', validator: validateCategoryId},
          type: {required: true, message: '请输入资讯位置', trigger: 'change'},
          publisherName: {required: true, message: '请输入资讯发布人', trigger: 'blur'},
          introduction: {required: true, message: '请输入资讯简介', trigger: 'blur'},
          content: {required: true, message: '请输入资讯内容', trigger: 'change'},
        },
        articleFormLoading: false
      }
    },
    created() {
      this.fetchData();
    },
    methods: {
      handleTextChange(val) {
        this.articleForm.content = val;
      },
      fetchData() {
        getTopLevelCategoryList()
          .then(response => {
            let temp = [{id: '', name: '全部'}];
            this.topLevelCategoryList = temp.concat(response.data);
            this.topLevelCategoryList_1 = response.data;
          });
        getArticleList(this.searchCondition)
          .then(response => {
            this.total = response.data.total;
            this.articleList = response.data.records;
          });
      },
      handleSizeChange(val) {
        this.searchCondition.pageSize = val;
        this.searchCondition.current = 1;
        this.fetchData();
      },
      handleCurrentChange(val) {
        this.searchCondition.current = val;
        this.fetchData();
      },
      handleTopLevelCategorySelectChange(type, val) {
        let temp = [{id: '', name: '全部'}];
        if (1 === type) {
          if (val === '') {
            this.secondLevelCategoryList = temp;
          } else {
            getSecondLevelCategoryListByParentId(val)
              .then(response => {
                this.secondLevelCategoryList = temp.concat(response.data);
              });
          }
        } else {
          if (val === '') {
            this.secondLevelCategoryList = [];
          } else {
            this.articleForm.secondLevelCategoryId = '';
            getSecondLevelCategoryListByParentId(val)
              .then(response => {
                this.secondLevelCategoryList_1 = response.data;
              });
          }
        }
      },
      searchArticleList() {
        this.searchCondition.current = 1;
        this.fetchData();
      },
      publishArticle(row) {
        this.$confirm('此操作将会发布资讯, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          publish(row.id).then(response => {
            this.$message({
              message: "发布成功",
              type: "success",
              duration: 2 * 1000
            });
            row.status = 1;
          });
        });
      },
      revokeArticle(row) {
        this.$confirm('此操作将会撤回资讯, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          revoke(row.id).then(response => {
            this.$message({
              message: "撤回成功",
              type: "success",
              duration: 2 * 1000
            });
            row.status = 0;
          })
        })
      },
      deleteArticleById(id) {
        this.$confirm('此操作将会删除资讯, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteArticleById(id).then(response => {
            this.$message({
              message: "删除成功",
              type: "success",
              duration: 2 * 1000
            });
            this.fetchData();
          })
        })
      },
      handleDialogClose() {
        if (this.articleFormLoading !== true) {
          this.clearArticleForm();
          this.$refs.articleForm.clearValidate();
          this.dialogFormVisible = false;
        } else {
          this.$message({
            message: "正在提交中...",
            type: "warning",
            duration: 2 * 1000
          });
        }
      },
      newArticle_click() {
        this.clearArticleForm();
        this.dialogFormVisible = true;
      },
      updateArticle_click(row) {
        this.articleForm.id = row.id;
        this.articleForm.title = row.title;
        this.articleForm.introduction = row.introduction;
        this.articleForm.topLevelCategoryId = row.topLevelCategoryId;
        this.articleForm.secondLevelCategoryId = row.secondLevelCategoryId;
        this.articleForm.topLevelCategoryName = row.topLevelCategoryName;
        this.articleForm.secondLevelCategoryName = row.secondLevelCategoryName;
        this.articleForm.content = row.content;
        this.articleForm.type = row.type;
        this.articleForm.publisherName = row.publisherName;
        getSecondLevelCategoryListByParentId(this.articleForm.topLevelCategoryId)
          .then(response => {
            this.secondLevelCategoryList_1 = response.data;
            this.dialogFormVisible = true;
            this.$nextTick(_ => {
              this.$refs.editor.setHtml(this.articleForm.content);
            });
          });
      },
      clearArticleForm() {
        this.articleForm = {
          title: '',
          id: '',
          topLevelCategoryId: '',
          secondLevelCategoryId: '',
          topLevelCategoryName: '',
          secondLevelCategoryName: '',
          type: '',
          publisherName: '',
          content: '',
          introduction: ''
        };
        this.$refs.editor.clear();
      },
      submitForm() {
        this.$refs.articleForm.validate(valid => {
          if (valid) {
            this.articleFormLoading = true;
            this.articleForm.topLevelCategoryName = this.topLevelCategoryList_1.filter(item => item.id === this.articleForm.topLevelCategoryId)[0].name;
            this.articleForm.secondLevelCategoryName = this.secondLevelCategoryList_1.filter(item => item.id === this.articleForm.secondLevelCategoryId)[0].name;
            if (!this.articleForm.id) {
              newArticle(this.articleForm).then(_ => {
                this.$message({
                  message: "新建资讯成功",
                  type: "success",
                  duration: 2 * 1000
                });
                this.searchCondition = {
                  type: '',
                  topLevelCategoryId: '',
                  secondLevelCategoryId: '',
                  begin: '',
                  end: '',
                  status: '',
                  title: '',
                  current: 1,
                  pageSize: 10
                };
                this.fetchData();
                this.articleFormLoading = false;
                this.handleDialogClose();
              }).catch(error => {
                this.articleFormLoading = false;
              });
            } else {
              updateArticle(this.articleForm).then(_ => {
                this.$message({
                  message: "修改资讯成功",
                  type: "success",
                  duration: 2 * 1000
                });
                this.searchCondition = {
                  type: '',
                  topLevelCategoryId: '',
                  secondLevelCategoryId: '',
                  begin: '',
                  end: '',
                  status: '',
                  title: '',
                  current: 1,
                  pageSize: 10
                };
                this.fetchData();
                this.articleFormLoading = false;
                this.handleDialogClose();
              }).catch(error => {
                this.articleFormLoading = false;
              });
            }
          } else {
            return false;
          }
        })
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

