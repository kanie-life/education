<template>
	<div class="loginWrapper" v-title data-title="登录">
		<div class="login-form-layout">
			<div>
				<div class="title_text_sty">
					<span class="span1_sty">教育管理系统</span>
					<span class="span1_sty">|</span>
					<span class="span1_sty">学生管理系统</span>
				</div>
				<el-button style="color:#fff;" type="text" size="mini" @click="forgotPassword">忘记密码？
				</el-button>
			</div>
			<div>
				<el-card>
					<el-form :inline="true" :model="loginForm" class="demo-form-inline">
						<el-form-item :error="nameError" prop="username">
							<el-input name="username" type="text" v-model="loginForm.username" autoComplete="on"
							 placeholder="请输入用户名" @input="nameInput">
							<span slot="prefix">
								<svg-icon icon-class="user"></svg-icon>
							</span>
							</el-input>
						</el-form-item>
						<el-form-item :error="passError" prop="password">
							<el-input :type="pwdType" name="password" v-model="loginForm.password"
							 autoComplete="on" placeholder="请输入密码" @input="passInput" @keyup.enter.native="handleLogin">
								<span slot="prefix">
									<svg-icon icon-class="password"></svg-icon>
								</span>
								<i v-if="showpass" slot="suffix" class="el-icon-view" @click="showPwd" />
								<i v-if="!showpass" slot="suffix" @click="showPwd">
									<svg-icon icon-class="eye"></svg-icon>
								</i>
							</el-input>
						</el-form-item>
						<el-form-item>
							<el-button :loading="loading" type="primary" @click.native.prevent="handleLogin">登录</el-button>
						</el-form-item>
					</el-form>
				</el-card>
			</div>
		</div>
		<div class="div2_sty">Copyright © 2026-2028 广东科技有限公司 备案号：<a :href="'http://www.beian.miit.gov.cn/'" target="_blank">粤ICP备xxxxxxxxx号-1</a></div>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				loginForm: {
					username: '',
					password: ''
				},
				nameError: '',
				passError: '',
				showpass: false,
				pwdType: 'password',
				loading: false
			}
		},
		methods: {
			nameInput() {
				this.nameError = ''
			},
			passInput() {
				this.passError = ''
			},
			showPwd() {
				if (this.pwdType === 'password') {
					this.pwdType = ''
					this.showpass = true
				} else {
					this.showpass = false
					this.pwdType = 'password'
				}
			},
			// 登录
			handleLogin() {
				const vm = this
				if (vm.loginForm.username === '') {
					this.nameError = '请输入登录账号'
				}
				if (vm.loginForm.password === '') {
					this.passError = '请输入登录密码'
				}
				if (vm.loginForm.username !== '' && vm.loginForm.password !== '') {
					vm.loading = true
					this.$store.dispatch('Login', vm.loginForm).then(() => {
					    this.loading = false
					    this.$router.push({ path: '/' })
					}).catch(e => {
					    console.log(e)
					    this.loading = false
					})
				}
			},
			//忘记密码
			forgotPassword() {

			}
		}
	}
</script>

<style scoped>
	.loginWrapper {
		background: url("../assets/login.jpg") no-repeat center bottom;
		background-size: cover;
		width: 100%;
		height: 100%;
		position: fixed;
		top: 0;
		left: 0;
	}

	.login-form-layout {
		position: absolute;
		top: 30%;
		left: 0;
		right: 0;
		width: 620px;
		margin: 0px auto;
	}

	.login-form-layout .el-form-item {
		margin-bottom: 0 !important;
	}

	.title_text_sty {
		color: #fff;
		display: inline-block;
		margin-right: 340px;
	}

	.title_text_sty .span1_sty {
		margin-left: 10px;
		margin-right: 1px;
		font-size: 14px;
	}

	.div2_sty {
		width: 620px;
		position: absolute;
		bottom: 2%;
		left: 0;
		right: 0;
		margin: 0px auto;
		color: #fff;
		font-size: 13px;
		text-align: center;
	}
</style>
