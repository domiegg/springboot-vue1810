<template>
	<div class="diy_edit page_tender_information" id="tender_information_edit">
		<div class='warp'>
			<div class='container'>
				<div class='row diy_edit_content_box'>
						<div v-if="$check_field('set','bidding_project') || $check_field('add','bidding_project') || $check_field('get','bidding_project')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								招标项目:
							</span>
						</div>
								<!-- 文本 -->
									<div class="diy_field diy_text">
							<input type="text" id="form_bidding_project" v-model="form['bidding_project']" placeholder="请输入招标项目" v-if="(form['bidding_project'] && $check_field('set','bidding_project')) || (!form['bidding_project'] && $check_field('add','bidding_project'))"  :disabled="disabledObj['bidding_project_isDisabled']"/>
							<span v-else-if="$check_field('get','bidding_project')">{{ form['${obj.name}'] }}</span>
						</div>
										</div>
							<div v-if="$check_field('set','bidding_classification') || $check_field('add','bidding_classification') || $check_field('get','bidding_classification')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								招标分类:
							</span>
						</div>
								<!-- 文本 -->
									<div class="diy_field diy_text">
							<input type="text" id="form_bidding_classification" v-model="form['bidding_classification']" placeholder="请输入招标分类" v-if="(form['bidding_classification'] && $check_field('set','bidding_classification')) || (!form['bidding_classification'] && $check_field('add','bidding_classification'))"  :disabled="disabledObj['bidding_classification_isDisabled']"/>
							<span v-else-if="$check_field('get','bidding_classification')">{{ form['${obj.name}'] }}</span>
						</div>
										</div>
							<div v-if="$check_field('set','tendering_unit') || $check_field('add','tendering_unit') || $check_field('get','tendering_unit')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								招标单位:
							</span>
						</div>
						<div class="diy_field diy_down">
							<select id="form_tendering_unit" :disabled="disabledObj['tendering_unit_isDisabled']" v-model="form['tendering_unit']" v-if="(form['tendering_unit'] && $check_field('set','tendering_unit')) || (!form['tendering_unit'] && $check_field('add','tendering_unit'))" >
								<option v-for="o in list_user_tendering_unit" :value="o['user_id']">
									{{o['nickname'] + '-' + o['username']}}
								</option>
							</select>
							<span v-else-if="$check_field('get','tendering_unit')">{{ form['tendering_unit'] }}</span>
						</div>
					</div>
							<div v-if="$check_field('set','responsible_unit') || $check_field('add','responsible_unit') || $check_field('get','responsible_unit')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								责任单位:
							</span>
						</div>
								<!-- 文本 -->
									<div class="diy_field diy_text">
							<input type="text" id="form_responsible_unit" v-model="form['responsible_unit']" placeholder="请输入责任单位" v-if="(form['responsible_unit'] && $check_field('set','responsible_unit')) || (!form['responsible_unit'] && $check_field('add','responsible_unit'))"  :disabled="disabledObj['responsible_unit_isDisabled']"/>
							<span v-else-if="$check_field('get','responsible_unit')">{{ form['${obj.name}'] }}</span>
						</div>
										</div>
							<div v-if="$check_field('set','tender_cover') || $check_field('add','tender_cover') || $check_field('get','tender_cover')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								招标封面:
							</span>
						</div>
								<!-- 图片 -->
						<input type="file" :disabled="disabledObj['tender_cover_isDisabled']" style="display: none;" id="form_img_tender_cover" title="tender_cover" @change="change_file($event.target.files,'tender_cover')"/>
						<!-- 修改权限 -->
						<div class="diy_field diy_img" v-if="form['tender_cover'] && $check_field('set','tender_cover')">
							<label for="form_img_tender_cover">
								<img :src="$fullUrl(form['tender_cover'])" />
							</label>
						</div>
						<!-- 添加权限 -->
						<div class="diy_field diy_img" v-else-if="!form['tender_cover'] && $check_field('add','tender_cover')">
							<label for="form_img_tender_cover">
								<div class="btn_add_img">
									<span>+</span>
								</div>
							</label>
						</div>
						<!-- 查询权限 -->
						<div class="diy_field diy_img" v-else-if="$check_field('get','tender_cover')">
							<img :src="$fullUrl(form['tender_cover'])" />
						</div>
							</div>
							<div v-if="$check_field('set','bidding_merchant') || $check_field('add','bidding_merchant') || $check_field('get','bidding_merchant')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								投标商家:
							</span>
						</div>
						<div class="diy_field diy_down">
							<select id="form_bidding_merchant" :disabled="disabledObj['bidding_merchant_isDisabled']" v-model="form['bidding_merchant']" v-if="(form['bidding_merchant'] && $check_field('set','bidding_merchant')) || (!form['bidding_merchant'] && $check_field('add','bidding_merchant'))" >
								<option v-for="o in list_user_bidding_merchant" :value="o['user_id']">
									{{o['nickname'] + '-' + o['username']}}
								</option>
							</select>
							<span v-else-if="$check_field('get','bidding_merchant')">{{ form['bidding_merchant'] }}</span>
						</div>
					</div>
							<div v-if="$check_field('set','supplier') || $check_field('add','supplier') || $check_field('get','supplier')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								供应商家:
							</span>
						</div>
								<!-- 文本 -->
									<div class="diy_field diy_text">
							<input type="text" id="form_supplier" v-model="form['supplier']" placeholder="请输入供应商家" v-if="(form['supplier'] && $check_field('set','supplier')) || (!form['supplier'] && $check_field('add','supplier'))"  :disabled="disabledObj['supplier_isDisabled']"/>
							<span v-else-if="$check_field('get','supplier')">{{ form['${obj.name}'] }}</span>
						</div>
										</div>
							<div v-if="$check_field('set','company_address') || $check_field('add','company_address') || $check_field('get','company_address')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								公司地址:
							</span>
						</div>
								<!-- 文本 -->
									<div class="diy_field diy_text">
							<input type="text" id="form_company_address" v-model="form['company_address']" placeholder="请输入公司地址" v-if="(form['company_address'] && $check_field('set','company_address')) || (!form['company_address'] && $check_field('add','company_address'))"  :disabled="disabledObj['company_address_isDisabled']"/>
							<span v-else-if="$check_field('get','company_address')">{{ form['${obj.name}'] }}</span>
						</div>
										</div>
							<div v-if="$check_field('set','bid_amount') || $check_field('add','bid_amount') || $check_field('get','bid_amount')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								投标金额:
							</span>
						</div>
								<!-- 数字 -->
						<div class="diy_field diy_number">
							<input type="number" id="form_bid_amount" v-model.number="form['bid_amount']" placeholder="请输入投标金额" v-if="(form['bid_amount'] && $check_field('set','bid_amount')) || (!form['bid_amount'] && $check_field('add','bid_amount'))" :disabled="disabledObj['bid_amount_isDisabled']" />
							<span v-else-if="$check_field('get','bid_amount')">{{ form['${obj.name}'] }}</span>
						</div>
							</div>
	




				</div>
				<div class="diy_edit_submit_box row">
					<div class="col-12">
						<div class="btn_box">
							<button class="btn_submit" @click="submit()">提交</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import mixin from "@/mixins/page.js";
	export default {
		mixins: [mixin],
		components: {},
		data() {
			return {
				url_get_obj: "~/api/tender_information/get_obj?",
				url_add: "~/api/tender_information/add?",
				url_set: "~/api/tender_information/set?",

				// 登录权限
				oauth: {
					"signIn": true,
					"user_group": []
				},

				// 查询条件
				query: {
						"bidding_project": "",
							"bidding_classification": "",
							"tendering_unit": 0,
							"responsible_unit": "",
							"tender_cover": "",
							"bidding_merchant": 0,
							"supplier": "",
							"company_address": "",
							"bid_amount": 0,
						"tender_information_id": 0,
				},

				obj: {
						"bidding_project":  '', // 招标项目
							"bidding_classification":  '', // 招标分类
							"tendering_unit": 0, // 招标单位
							"responsible_unit":  '', // 责任单位
							"tender_cover":  '', // 招标封面
							"bidding_merchant": 0, // 投标商家
							"supplier":  '', // 供应商家
							"company_address":  '', // 公司地址
							"bid_amount":  0, // 投标金额
						"tender_information_id": 0,
				},

				// 表单字段
				form: {
						"bidding_project":  '', // 招标项目
							"bidding_classification":  '', // 招标分类
							"tendering_unit": 0, // 招标单位
							"responsible_unit":  '', // 责任单位
							"tender_cover":  '', // 招标封面
							"bidding_merchant": 0, // 投标商家
							"supplier":  '', // 供应商家
							"company_address":  '', // 公司地址
							"bid_amount":  0, // 投标金额
						"tender_information_id": 0,
				},
				disabledObj:{
						"bidding_project_isDisabled": false,
							"bidding_classification_isDisabled": false,
							"tendering_unit_isDisabled": false,
							"responsible_unit_isDisabled": false,
							"tender_cover_isDisabled": false,
							"bidding_merchant_isDisabled": false,
							"supplier_isDisabled": false,
							"company_address_isDisabled": false,
							},

										// 用户列表
				list_user_tendering_unit: [],
											// 用户列表
				list_user_bidding_merchant: [],
							
				// ID字段
				field: "tender_information_id",

			}
		},
		methods: {
			
				
						/**
			 * 获取责任单位用户列表
			 */
			async get_list_user_tendering_unit() {
				// if(this.user_group !== "管理员" && this.form["tendering_unit"] === 0) {
				//     this.form["tendering_unit"] = this.user.user_id;
				// }
				var json = await this.$get("~/api/user/get_list?user_group=责任单位");
				if(json.result && json.result.list){
					this.list_user_tendering_unit = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
		
				
				
						/**
			 * 获取供应商家用户列表
			 */
			async get_list_user_bidding_merchant() {
				// if(this.user_group !== "管理员" && this.form["bidding_merchant"] === 0) {
				//     this.form["bidding_merchant"] = this.user.user_id;
				// }
				var json = await this.$get("~/api/user/get_list?user_group=供应商家");
				if(json.result && json.result.list){
					this.list_user_bidding_merchant = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
					async get_user_session_bidding_merchant(){
				var _this = this;
				var json = await this.$get("~/api/user_group/get_obj?name=供应商家");
				if(json.result && json.result.obj){
					var source_table = json.result.obj.source_table;
					var user_id = _this.$store.state.user.user_id;
					if (user_id){
						var url = "~/api/"+source_table+"/get_obj?"
						this.$get(url, {"user_id":_this.$store.state.user.user_id}, function(res) {
							if (res.result && res.result.obj) {
								var arr = []
								for (let key in res.result.obj) {
									arr.push(key)
								}
								var arrForm = []
								for (let key in _this.form) {
									arrForm.push(key)
								}
								_this.form["bidding_merchant"] = user_id
								_this.disabledObj['bidding_merchant' + '_isDisabled'] = true
								for (var i=0;i<arr.length;i++){
                  if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
                    for (var j = 0; j < arrForm.length; j++) {
                      if (arr[i] === arrForm[j]) {
                        if (arr[i] !== "bidding_merchant") {
                          _this.form[arrForm[j]] = res.result.obj[arr[i]]
                          _this.disabledObj[arrForm[j] + '_isDisabled'] = true
                          break;
                        }
                      }
                    }
                  }
								}
							}
						});
					}
				}
				else if(json.error){
					console.error(json.error);
				}
			},
	
				
				
				
	
			/**
			 * 修改文件
			 * @param { Object } files 上传文件对象
			 * @param { String } str 表单的属性名
			 */
			change_file(files, str) {
				var form = new FormData();
				form.append("file", files[0]);
				this.$post("~/api/tender_information/upload?", form, (res) => {
					if (res.result) {
						this.form[str] = res.result.url;
					} else if (res.error) {
						this.$toast(res.error.message);
					}
				});
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_before(param){
				var form = $.db.get("form");
				// if (form) {
        //   delete(form.examine_state)
        //   delete(form.examine_reply)
        //   this.obj = $.push(this.obj ,form);
				// 	this.form = $.push(this.form ,form);
				// }
				// var arr = []
				// for (let key in form) {
				// 	arr.push(key)
				// }
				// for (var i=0;i<arr.length;i++){
				// 	this.disabledObj[arr[i] + '_isDisabled'] = true
				// }
        if (form) {
          var arr = []
          for (let key in form) {
            arr.push(key)
          }
          var arrForm = []
          for (let key in this.form) {
            arrForm.push(key)
          }
          for (var i=0;i<arr.length;i++){
            if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
              for (var j = 0; j < arrForm.length; j++) {
                if (arrForm[j] === arr[i]) {
                  this.form[arrForm[j]] = form[arr[i]]
                  this.obj[arrForm[j]] = form[arr[i]]
                  this.disabledObj[arrForm[j] + '_isDisabled'] = true
                  break;
                }
              }
            }
          }
        }
																		
        $.db.del("form");
				return param;
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json ,func){
				// var form = $.db.get("form");
				// var obj = Object.assign({} ,form ,this.obj);
				// if (obj) {
        //   delete(obj.examine_state)
        //   delete(obj.examine_reply)
				// 	this.obj = $.push(this.obj ,obj);
				// }
				// if (form) {
        //   delete(form.examine_state)
        //   delete(form.examine_reply)
				// 	this.form = $.push(this.form ,form);
				// }

				if(func){
					func(json);
				}
			},

		},
		created() {
												this.get_list_user_tendering_unit();
												this.get_user_session_bidding_merchant();
					this.get_list_user_bidding_merchant();
												},
	}
</script>

<style>




</style>
