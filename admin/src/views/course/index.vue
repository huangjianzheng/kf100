<template>
  <div class="app-container">
    <span class="tops">课程列表</span><hr>
    <div class="tableDiv">
      <el-table :data="records" border style="width: 100%" align="center">
        <el-table-column prop="title" label="课程标题" width="600" align="center">
        </el-table-column>
        <el-table-column prop="lecturerName" label="讲师" width="300" align="center">
        </el-table-column>
        <el-table-column prop="visitNum" label="浏览次数" width="300" align="center">
        </el-table-column>
        <el-table-column prop="status" label="状态" width="300" align="center">
          <template slot-scope="scope">
            <span v-if="1===scope.row.status">未发布</span>
            <span v-else>已发布</span>
          </template>
        </el-table-column>
        <el-table-column fixed="right" label="操作" align="center">
          <template slot-scope="scope">
            <el-button type="text" size="small" v-if="1===scope.row.status" @click="recall(scope.row.id)">发布 </el-button>
            <el-button type="text" size="small" v-else @click="release(scope.row.id)">撤回</el-button>
            <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="page-bar">
        <el-button type="primary"> 新 建 </el-button>
        <el-pagination class="fr" @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="current" :page-sizes="[5,10,20,30]" :page-size="size" layout="total,  prev, pager, next, sizes,jumper" :total="total">
        </el-pagination>
      </div>

    </div>

  </div>
</template>

<script>
import { courseList, updateStatus } from "@/api/course";
export default {
    data() {
        return {
            uploadImageUrl: process.env.UPLOAD_IMAGE_URL,
            imageTypes: process.env.IMAGE_TYPES,
            imageMaxSize: process.env.IMAGE_MAX_SIZE,
            imagePath: process.env.IMAGE_PATH,

            current: 1, //单前页数
            pageSize: 10, //每页记录数
            records: [],
            total: 0 //总记录数
        };
    },
    created() {
        this.getCourseList();
    },
    methods: {
        getCourseList() {
            courseList(this.current, this.pageSize).then(response => {
                this.records = response.data.records;
                this.total = response.data.total;
            });
        },
        handleSizeChange(val) {
            console.log(`每页 ${val} 条`);
            this.pageSize = val;
            this.current = 1;
            this.getCourseList();
        },
        handleCurrentChange(val) {
            console.log(`当前页: ${val}`);
            this.current = val;
            this.getCourseList();
        },
        recall(id) {
            //撤回事件
            this.$confirm("即将发布该课程, 是否继续?", "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning"
            })
                .then(() => {
                    updateStatus(id, 0).then(response => {
                        //0.显示，1隐藏
                        this.$message({
                            type: "success",
                            message: "发布成功!"
                        });
                        this.getCourseList();
                    });
                })
                .catch(() => {
                    this.$message({
                        type: "info",
                        message: "已取消发布"
                    });
                });
        },
        release(id) {
            //发布事件
            this.$confirm("即将撤回该课程, 是否继续?", "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning"
            })
                .then(() => {
                    updateStatus(id, 1).then(response => {
                        //0.显示，1隐藏
                        this.$message({
                            type: "success",
                            message: "撤回成功!"
                        });
                        this.getCourseList();
                    });
                })
                .catch(() => {
                    this.$message({
                        type: "info",
                        message: "已取消撤回"
                    });
                });
        }
    }
};
</script>

<style scoped>
.tops {
    font-size: 26px;
}
.tableDiv {
    margin-top: 30px;
}
.butDiv {
    margin-top: 20px;
    border: 1px solid;
    height: 100px;
}
.fr {
    float: right;
}
.page-bar {
    margin-top: 20px;
}
</style>

