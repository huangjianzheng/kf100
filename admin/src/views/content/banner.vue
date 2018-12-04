<template>
  <div class="app-container">
    <div class="filter-bar">
      <el-button type="primary" icon="el-icon-plus" plain class="fr" @click="newBanner_click">新增轮播图</el-button>
    </div>
    <el-table :data="bannerList" style="width: 100%" border fit max-height="700">
      <el-table-column prop="id" label="编号" align="center"></el-table-column>
      <el-table-column prop="title" label="标题" align="center"></el-table-column>
      <el-table-column prop="logo" label="轮播图" align="center" width="254">
        <template slot-scope="scope">
          <img :src="imagePath + scope.row.logo" width="234" height="100"/>
        </template>
      </el-table-column>
      <el-table-column prop="createTime" label="创建时间" align="center"></el-table-column>
      <el-table-column prop="sequence" label="序号" align="center"></el-table-column>
      <el-table-column prop="status" label="状态" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.status === 0">未发布</span>
          <span v-if="scope.row.status === 1">已发布</span>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" align="center">
        <template slot-scope="scope">
          <el-button type="text" size="small" v-if="scope.row.status === 0" @click="publishBanner(scope.row)">
            发布
          </el-button>
          <el-button type="text" size="small" v-if="scope.row.status === 1" @click="revokeBanner(scope.row)">
            撤回
          </el-button>
          <el-button type="text" size="small" @click="updateBanner_click(scope.row)">
            修改
          </el-button>
          <el-button type="text" size="small" @click="deleteBanner_click(scope.row.id)">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="page-bar fr">
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                     :current-page="current" :page-sizes="[5, 10, 20, 30]"
                     :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper"
                     :total="total">
      </el-pagination>
    </div>
    <el-dialog title="新增/修改轮播图" :visible.sync="dialogFormVisible" :before-close="handleDialogClose" top="8vh"
               width="30%" :close-on-click-modal="false">
      <el-form ref="bannerForm" :model="bannerForm" :rules="rules" label-width="100px">
        <el-form-item label="序号：" prop="sequence">
          <el-input v-model="bannerForm.sequence" placeholder="请输入序号"/>
        </el-form-item>
        <el-form-item label="标题：" prop="title">
          <el-input v-model="bannerForm.title" placeholder="请输入标题"/>
        </el-form-item>
        <el-form-item label="轮播图片：" prop="imageUrl">
          <el-upload class="avatar-uploader" :action="uploadImageUrl" :show-file-list="false"
                     :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
            <img v-if="bannerForm.logo" :src="imagePath+bannerForm.logo" width="243" height="100" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon dash" width="243" height="100"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="简介：" prop="introduction">
          <el-input v-model="bannerForm.introduction" placeholder="请输入简介" type="textarea"
                    :autosize="{ minRows: 3, maxRows: 6}"/>
        </el-form-item>
        <el-form-item label="链接：" prop="url">
          <el-input v-model="bannerForm.url" placeholder="请输入链接"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="handleDialogClose">取 消</el-button>
        <el-button type="primary" @click="submitForm" :loading="bannerFormLoading">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {newBanner,updateBanner,deleteBannerById, getBannerList, publish, revoke} from "../../api/banner";

  let canUpload = false;
  export default {
    data() {
      const validateImageSize = (rule, value, callback) => {
        if (canUpload) {
          return callback();
        }
        return callback(new Error('图片上传比例不正确,图片比例（宽：857，高：353）'));
      };
      const validateSequence = (rule, value, callback) => {
        const reg = /^[0-9]*[1-9][0-9]*$/;
        if (reg.test(value)) {
          return callback();
        }
        return callback(new Error('请输入正整数'));
      };
      const validateTitle = (rule, value, callback) => {
        if (value && value.length <= 32) {
          return callback();
        }
        return callback(new Error('标题不超过32个字'));
      };
      const validateIntroduction = (rule, value, callback) => {
        if (value && value.length <= 200) {
          return callback();
        }
        return callback(new Error('简介不超过200个字'));
      };
      return {
        uploadImageUrl: process.env.UPLOAD_IMAGE_URL,
        imageTypes: process.env.IMAGE_TYPES,
        imageMaxSize: process.env.IMAGE_MAX_SIZE,
        imagePath: process.env.IMAGE_PATH,
        current: 1,
        pageSize: 5,
        total: 0,
        bannerList: [],
        dialogFormVisible: false,
        bannerForm: {
          id: '',
          logo: '',
          title: '',
          sequence: '',
          introduction: '',
          url: '',
          type: 1
        },
        bannerFormLoading: false,
        rules: {
          url: [
            {required: true, message: '链接不能为空', trigger: 'blur'},
          ],
          logo: [
            {required: true, message: '图片不能为空', trigger: 'blur'},
            {trigger: 'blur', validator: validateImageSize}
          ],
          title: [
            {required: true, message: '标题不能为空', trigger: 'blur'},
            {trigger: 'blur', validator: validateTitle}
          ],
          introduction: [
            {required: true, message: '标题不能为空', trigger: 'blur'},
            {trigger: 'blur', validator: validateIntroduction}
          ],
          sequence: [
            {required: true, message: '序号不能为空', trigger: 'blur'},
            {trigger: 'blur', validator: validateSequence},
          ]
        }
      }
    },
    created() {
      this.fetchData();
    },
    methods: {
      handleAvatarSuccess(res, file) {
        this.bannerForm.logo = res.data;
      },
      beforeAvatarUpload(file) {
        return new Promise(function (resolve, reject) {
          let _URL = window.URL || window.webkitURL;
          let img = new Image();
          img.onload = function () {
            let width = img.width;
            let height = img.height;
            let resultW = width / 857;
            let resultH = height / 353;
            let flagR = (resultW === resultH);
            canUpload = flagR;
            flagR ? resolve() : reject();
          };
          img.src = _URL.createObjectURL(file);
        }).then(() => {
          return file;
        }, () => {
          this.$message.error('上传图片比例不正确,图片比例（宽：857，高：353）', 15000);
          this.form.imageUrl = null;
          return Promise.reject();
        });
      },
      fetchData() {
        getBannerList(this.current, this.pageSize).then(response => {
          this.bannerList = response.data.records;
          this.total = response.data.total;
        })
      },
      handleSizeChange(val) {
        this.pageSize = val;
        this.current = 1;
        this.fetchData();
      },
      handleCurrentChange(val) {
        this.current = val;
        this.fetchData();
      },
      publishBanner(row) {
        this.$confirm('此操作将会发布轮播图, 是否继续?', '提示', {
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
      revokeBanner(row) {
        this.$confirm('此操作将会撤回轮播图, 是否继续?', '提示', {
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
      updateBanner_click(row) {
        this.bannerForm.id = row.id;
        this.bannerForm.logo = row.logo;
        this.bannerForm.title = row.title;
        this.bannerForm.sequence = row.sequence;
        this.bannerForm.introduction = row.introduction;
        this.bannerForm.url = row.url;
        this.bannerForm.type = 1;
        this.dialogFormVisible = true;
      },
      newBanner_click() {
        this.clearForm();
        this.dialogFormVisible = true;
      },
      clearForm() {
        this.bannerForm = {
          id: '',
          logo: '',
          title: '',
          sequence: '',
          introduction: '',
          url: '',
          type: 1
        };
      },
      deleteBanner_click(id) {
        this.$confirm('此操作将会删除轮播图, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteBannerById(id).then(response => {
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
        if (this.bannerFormLoading !== true) {
          this.clearForm();
          this.$refs.bannerForm.clearValidate();
          this.dialogFormVisible = false;
        } else {
          this.$message({
            message: "正在提交中...",
            type: "warning",
            duration: 2 * 1000
          });
        }
      },
      submitForm() {
        this.$refs.bannerForm.validate(valid => {
          if (valid) {
            this.bannerFormLoading = true;
            if (!this.bannerForm.id) {
              newBanner(this.bannerForm).then(_ => {
                this.$message({
                  message: "新建轮播图成功",
                  type: "success",
                  duration: 2 * 1000
                });
                this.current = 1;
                this.fetchData();
                this.bannerFormLoading = false;
                this.handleDialogClose();
              }).catch(error => {
                this.bannerFormLoading = false;
              });
            } else {
              updateBanner(this.bannerForm).then(_ => {
                this.$message({
                  message: "修改轮播图成功",
                  type: "success",
                  duration: 2 * 1000
                });
                this.current = 1;
                this.fetchData();
                this.bannerFormLoading = false;
                this.handleDialogClose();
              }).catch(error => {
                this.bannerFormLoading = false;
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
  .page-bar {
    padding: 10px 0px 10px 0px;
  }

  .fr {
    float: right;
  }

  .filter-bar {
    height: 60px;
  }

  .filter-bar span {
    margin-left: 20px;
  }

  .filter-bar .el-select {
    width: 130px;
  }

  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }

  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }

  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 243px;
    height: 100px;
    line-height: 100px;
    text-align: center;
  }

  .avatar {
    width: 335px;
    height: 114px;
    display: block;
  }

  .dash {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
  }

  .el-input {
    width: 400px;
  }

  .el-textarea {
    width: 400px;
  }
</style>

