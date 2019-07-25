<template>
    <b-container>        
        <div>      
            <h1 class="title">
                File Upload
            </h1>
            <h2 class="subtitle">
                Vue | Nuxt | Spring Boot
            </h2>
            <div class="p-4 bg-light">
                <b-form @submit.prevent="onSubmit">
                    <b-alert show :variant="notificationVariant" v-if="notificationTitle && notificationMessage">
                        <strong>{{notificationTitle}}</strong> {{notificationMessage}}
                    </b-alert>                    
                    <b-form-file
                        v-model="file"
                        :state="Boolean(file)"
                        placeholder="Choose a file from your computer..."
                        drop-placeholder="Drop file here..."
                        ></b-form-file>
                    <div v-if="Boolean(file)" class="mt-3">Selected file: <i>{{ file ? file.name : '' }}</i></div>
                    <div class="mt-4 text-right">
                        <button type="button" class="btn btn-secondary" @click="clear">Clear</button>
                        <button type="submit" class="btn btn-primary">Upload</button>                        
                    </div>
                </b-form>
            </div>
            <div v-if="uploadedFile">
                <div class="text-left mt-3 mb-2">
                    <h5>Last uploaded file</h5>                    
                </div>
                <div class="mt-3 p-4 bg-light text-left">                
                    <pre>{{uploadedFile}}</pre>
                </div>
            </div>
            <div class="mt-4" v-if="items.length > 0">
                <div class="text-left mb-2">
                    <h5>All uploaded files<button type="button" class="btn btn-link" @click="reset">(Delete all)</button></h5>                    
                </div>
                <div>
                    <b-table
                        :striped="striped"
                        :bordered="bordered"                        
                        :small="small"
                        :hover="hover"                        
                        :items="items"
                        :fields="fields"
                        >
                        <template slot="createdTime" slot-scope="scope">                        
                            {{ scope.item.createdTime | datetimeFormat }}
                        </template>
                    </b-table>
                </div>
            </div>
        </div>
    </b-container>
</template>

<script>
    import Logo from '~/components/Logo.vue';
    import Vue from 'vue';
    import moment from 'moment';
    
    Vue.filter('datetimeFormat', function (value) {
        return moment(value).format('MM/DD/YYYY hh:mm:ss');
    });

    export default {
        data() {
            return {
                file: null,
                uploadedFile: null,
                notificationVariant: 'primary',
                fields: ['id', 'originalFilename', 'storedFilename', 'contentType', 'fileSize', 'createdTime'],
                items: [],
                striped: true,
                bordered: true,
                small: true,
                hover: true,
                notificationMessage: '',
                notificationTitle: ''
            };
        },
        async mounted() {
            // display all registered files on page load
            await this.$axios.get('/file/list')
                    .then(response => {
                        this.items = response.data.object;
                    })
                    .catch(error => {
                        console.log(JSON.stringify('Error:  ' + error));
                    });
        },
        methods: {
            async onSubmit() {
                if (this.file === null) {
                    this.notificationVariant = 'warning';
                    this.notificationTitle = 'Impossible!';
                    this.notificationMessage = 'You must select a file from your system.';
                    return;
                } else if (this.file.size / 1024 / 1024 > 20) {
                    this.notificationVariant = 'warning';
                    this.notificationTitle = 'Hey!';
                    this.notificationMessage = 'You cannot upload files larger than 20MB.';
                    return;
                }

                this.notificationTitle = '';
                this.notificationMessage = '';

                let headers = {
                    'Content-Type': 'multipart/form-data'
                };

                let body = new FormData();
                body.append('file', this.file);

                let errorFound = false;

                // upload the file
                await this.$axios.post('/file/upload', body, {headers: headers})
                        .then(response => {
                            console.log('Uploaded file.');

                            this.uploadedFile = response.data.object;
                            this.notificationVariant = 'success';
                            this.notificationTitle = 'Good!';
                            this.notificationMessage = 'Your file has been successfuly uploaded.';
                            
                            this.file = null;
                        })
                        .catch(error => {
                            console.log('Error: ' + error.response.data.message);
                            errorFound = true;

                            this.notificationVariant = 'danger';
                            
                            if (error.response.status === 413) {
                                this.notificationTitle = error.response.data.message.split('|')[0];
                                this.notificationMessage = error.response.data.message.split('|')[1];
                            } else {
                                this.notificationTitle = 'Oops!';
                                this.notificationMessage = 'Internal server error. Please check API logs for more information.';
                            }
                        });

                if (!errorFound) {
                    // once the file is uploaded then update the file list
                    await this.$axios.get('/file/list')
                            .then(response => {
                                this.items = response.data.object;
                            })
                            .catch(error => {
                                console.log('Error: ' + error.response.data.message);
                            });
                }

            },
            async reset() {
                this.notificationTitle = '';
                this.notificationMessage = '';
                this.file = null;
                this.uploadedFile = null;

                let errorFound = false;

                // delete all registered files
                await this.$axios.$delete('/file/reset')
                        .then(response => {
                            console.log('Removed data.');
                        })
                        .catch(error => {
                            console.log('Error: ' + error.response.data.message);
                            errorFound = true;
                        });

                if (!errorFound) {
                    // once the file is uploaded then update the file list
                    await this.$axios.get('/file/list')
                            .then(response => {
                                this.items = response.data.object;
                            })
                            .catch(error => {
                                console.log('Error: ' + error.response.data.message);
                            });
                }
            },
            clear() {
                this.notificationTitle = '';
                this.notificationMessage = '';
                
                this.file = null;
                this.uploadedFile = null;
            }
        }
    }
</script>

<style>
    .container {
        margin: 0 auto;
        min-height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
        text-align: center;
    }

    .title {
        font-family: 'Quicksand', 'Source Sans Pro', -apple-system, BlinkMacSystemFont,
            'Segoe UI', Roboto, 'Helvetica Neue', Arial, sans-serif;
        display: block;
        font-weight: 300;
        font-size: 100px;
        color: #35495e;
        letter-spacing: 1px;
    }

    .subtitle {
        font-weight: 300;
        font-size: 42px;
        color: #526488;
        word-spacing: 5px;
        padding-bottom: 15px;
    }

    .links {
        padding-top: 15px;
    }
</style>
