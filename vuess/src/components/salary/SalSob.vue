<template>
  <div>
    <el-container>
      <el-header
        style="padding: 0px;display:flex;justify-content:space-between;align-items: center"
      >
        <div style="display: inline">
          <el-input
            placeholder="通过车牌号搜索车辆,记得回车哦..."
            clearable
            style="width: 300px;margin: 0px;padding: 0px;"
            size="mini"
            :disabled="advanceSearchViewVisible"
            @keyup.enter.native="searchEmp"
            prefix-icon="el-icon-search"
            v-model="keywords"
          ></el-input>
          <el-button
            type="primary"
            size="mini"
            style="margin-left: 5px"
            icon="el-icon-search"
            @click="searchEmp"
          >搜索</el-button>
        </div>
        <div style="margin-left: 5px;margin-right: 20px;display: inline">
          <el-button type="primary" size="mini" icon="el-icon-plus" @click="showAddEmpView">添加车辆</el-button>
        </div>
      </el-header>
      <el-main style="padding-left: 0px;padding-top: 0px">
        <div>
          <el-table
            :data="emps"
            v-loading="tableLoading"
            border
            stripe
            size="mini"
            style="width: 100%"
          >
            <el-table-column prop="vehicleType" label="车辆类型" align="center">
              <template slot-scope="scope">{{ scope.row.vehicleType}}</template>
            </el-table-column>
            <el-table-column prop="licensePlateNumber" label="车牌号" align="center">
              <template slot-scope="scope">{{ scope.row.licensePlateNumber}}</template>
            </el-table-column>
            <el-table-column prop="seating" label="座位数" align="center">
              <template slot-scope="scope">{{ scope.row.seating}}</template>
            </el-table-column>
            <el-table-column prop="speedometer" label="里程表" align="center">
              <template slot-scope="scope">{{ scope.row.speedometer}}</template>
            </el-table-column>
            <el-table-column prop="manufacturer" label="生产厂家" align="center">
              <template slot-scope="scope">{{ scope.row.manufacturer}}</template>
            </el-table-column>
            <el-table-column prop="state" label="状态" align="center">
              <template slot-scope="scope">{{ scope.row.state}}</template>
            </el-table-column>
            <el-table-column prop="brandName" label="品牌名称" align="center">
              <template slot-scope="scope">{{ scope.row.brandName}}</template>
            </el-table-column>
            <el-table-column prop="engineNo" label="发动机号" align="center">
              <template slot-scope="scope">{{ scope.row.engineNo}}</template>
            </el-table-column>
            <el-table-column prop="purchaseTime" label="购置时间" align="center">
              <template slot-scope="scope">{{ scope.row.purchaseTime | formatDate}}</template>
            </el-table-column>
            <el-table-column fixed="right" label="操作">
              <template slot-scope="scope">
                <el-button
                  @click="showEditEmpView(scope.row)"
                  style="padding: 3px 4px 3px 4px;margin: 2px"
                  size="mini"
                >编辑</el-button>
                <el-button
                  type="danger"
                  style="padding: 3px 4px 3px 4px;margin: 2px"
                  size="mini"
                  @click="deleteEmp(scope.row)"
                >删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-main>
    </el-container>
    <el-form
      :model="form"
      :rules="rules"
      ref="addCarForm"
      style="margin: 0px;padding: 0px;"
    >
      <div style="text-align: left">
        <el-dialog
          :title="dialogTitle"
          style="padding: 0px;"
          :close-on-click-modal="false"
          :visible.sync="dialogVisible"
          width="77%"
        >
          <el-row>
            <el-col :span="6">
              <el-form-item label="车辆类型:" prop="vehicleType">
                <el-input
                  prefix-icon="el-icon-edit"
                  v-model="form.vehicleType"
                  size="mini"
                  style="width: 150px"
                  placeholder="请输入车辆类型"
                ></el-input>
              </el-form-item>
              <el-form-item label="生产厂家:" prop="manufacturer">
                <el-input
                  prefix-icon="el-icon-edit"
                  v-model="form.manufacturer"
                  size="mini"
                  style="width: 150px"
                  placeholder="请输入生产厂家"
                ></el-input>
              </el-form-item>
              <el-form-item label="里程表: " prop="speedometer">
                <el-input
                  prefix-icon="el-icon-edit"
                  v-model="form.speedometer"
                  size="mini"
                  style="width: 150px"
                  placeholder="请输入车辆里程表"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="车牌号:" prop="licensePlateNumber">
                <el-input
                  prefix-icon="el-icon-edit"
                  v-model="form.licensePlateNumber"
                  size="mini"
                  style="width: 150px"
                  placeholder="请输入车牌号"
                ></el-input>
              </el-form-item>
              <el-form-item label="车辆状态:" prop="state">
                <!-- <el-input
                  prefix-icon="el-icon-edit"
                  v-model="form.state"
                  size="mini"
                  style="width: 150px"
                  placeholder="请输入车辆状态"
                ></el-input>-->
                <el-select
                  v-model="form.state"
                  size="mini"
                  style="width: 150px"
                  placeholder="请选择车辆状态"
                >
                  <el-option label="已出车" value="已出车"></el-option>
                  <el-option label="未出车" value="未出车"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="座位数:" prop="seating">
                <el-input
                  prefix-icon="el-icon-edit"
                  v-model="form.seating"
                  size="mini"
                  style="width: 150px"
                  placeholder="请输入载客位数"
                ></el-input>
              </el-form-item>
              <el-form-item label="品牌名称:" prop="brandName">
                <el-input
                  prefix-icon="el-icon-edit"
                  v-model="form.brandName"
                  size="mini"
                  style="width: 150px"
                  placeholder="请输入车辆品牌"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="发动机号:" prop="engineNo">
                <el-input
                  prefix-icon="el-icon-edit"
                  v-model="form.engineNo"
                  size="mini"
                  style="width: 150px"
                  placeholder="请输入发动机号"
                ></el-input>
              </el-form-item>
              <el-form-item label="购置时间:" prop="purchaseTime">
                <el-date-picker
                  v-model="form.purchaseTime"
                  size="mini"
                  style="width: 150px"
                  type="date"
                  value-format="yyyy-MM-dd"
                  format="yyyy-MM-dd"
                  placeholder="购置时间"
                ></el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>
          <span slot="footer" class="dialog-footer">
            <el-button size="mini" @click="cancelEidt">取 消</el-button>
            <el-button size="mini" type="primary" @click="addEmp('addCarForm')">确 定</el-button>
          </span>
        </el-dialog>
      </div>
    </el-form>
  </div>
</template>
<script>
export default {
  data() {
    return {
      emps: [],
      form: {},
      keywords: "",
      dialogTitle: "",

      dialogVisible: false,
      tableLoading: false,
      advanceSearchViewVisible: false,

      rules: {
        vehicleType: [
          { required: true, message: "必填:车辆类型", trigger: "blur" }
        ],
        licensePlateNumber: [
          { required: true, message: "必填:车牌号", trigger: "blur" }
        ],
        seating: [{ required: true, message: "必填:座位数", trigger: "blur" }],
        purchaseTime: [
          {
            required: true,
            message: "必填:购置时间",
            trigger: "blur"
          }
        ],
        brandName: [
          { required: true, message: "必填:品牌名称", trigger: "blur" }
        ],
        state: [{ required: true, message: "必填:状态", trigger: "change" }],
        manufacturer: [
          { required: true, message: "必填:生产厂家", trigger: "blur" }
        ],
        speedometer: [
          { required: true, message: "必填:里程表", trigger: "blur" }
        ],
        engineNo: [
          { required: true, message: "必填:发动机号", trigger: "blur" }
        ]
      }
    };
  },
  mounted: function() {
    this.loadEmps();
  },
  methods: {
    cancelSearch() {
      this.advanceSearchViewVisible = false;
      this.emptyEmpData();
      this.loadEmps();
    },
    deleteEmp(row) {
      //删除操作
      this.$confirm(
        "此操作将永久删除车牌号为: " +
          row.licensePlateNumber +
          "   的车辆, 是否继续?",
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }
      )
        .then(() => {
          this.doDelete(row.id);
        })
        .catch(() => {});
    },
    doDelete(ids) {
      this.tableLoading = true;
      var _this = this;
      this.deleteRequest("/car/deleteCar/" + ids).then(resp => {
        _this.tableLoading = false;
        if (resp && resp.status == 200) {
          var data = resp.data;
          _this.loadEmps();
        }
      });
    },
    searchEmp() {
      this.loadEmps();
    },
    loadEmps() {
      var _this = this;
      this.tableLoading = true;
      if (_this.keywords != "" && _this.keywords != null) {
        this.getRequest(
          "/car/getCarByLicensePlateNumber?licensePlateNumber=" + this.keywords
        ).then(resp => {
          this.tableLoading = false;
          if (resp && resp.status == 200) {
            var data = resp.data;
            _this.emps = data;
          }
        });
      } else {
        this.getRequest("/car/getCar").then(resp => {
          this.tableLoading = false;
          if (resp && resp.status == 200) {
            var data = resp.data;
            _this.emps = data;
          }
        });
      }
    },
    addEmp(formName) {
      var _this = this;
      this.$refs[formName].validate(valid => {
        if (valid) {
          if (this.form.id) {
            //更新
            this.tableLoading = true;
            this.putRequest("/car/updateCar", this.form).then(resp => {
              _this.tableLoading = false;
              if (resp && resp.status == 200) {
                var data = resp.data;
                _this.dialogVisible = false;
                _this.emptyEmpData();
                _this.loadEmps();
              }
            });
          } else {
            //添加
            this.tableLoading = true;
            this.postRequest("/car/addCar", this.form).then(resp => {
              _this.tableLoading = false;
              if (resp && resp.status == 200) {
                var data = resp.data;
                _this.dialogVisible = false;
                _this.emptyEmpData();
                _this.loadEmps();
              }
            });
          }
        } else {
          return false;
        }
      });
    },
    cancelEidt() {
      //取消
      this.dialogVisible = false;
      this.emptyEmpData();
    },
    showEditEmpView(row) {
      //点击编辑
      this.dialogTitle = "编辑车辆";
      this.form = row;
      this.dialogVisible = true;
    },
    showAddEmpView() {
      this.emptyEmpData();
      this.dialogTitle = "添加车辆";
      this.dialogVisible = true;
    },
    emptyEmpData() {
      this.form = {
        vehicleType: "",
        licensePlateNumber: "",
        seating: "",
        speedometer: "",
        manufacturer: "",
        state: "",
        brandName: "",
        engineNo: "",
        purchaseTime: ""
      };
    }
  }
};
</script>
<style>
.el-dialog__body {
  padding-top: 0px;
  padding-bottom: 0px;
}

.slide-fade-enter-active {
  transition: all 0.8s ease;
}

.slide-fade-leave-active {
  transition: all 0.8s cubic-bezier(1, 0.5, 0.8, 1);
}

.slide-fade-enter,
.slide-fade-leave-to {
  transform: translateX(10px);
  opacity: 0;
}
</style>
