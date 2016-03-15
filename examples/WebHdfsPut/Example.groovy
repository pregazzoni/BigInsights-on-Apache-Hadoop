/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import groovy.json.JsonSlurper
import org.apache.hadoop.gateway.shell.Hadoop
import org.apache.hadoop.gateway.shell.hdfs.Hdfs

env = System.getenv()
gateway = env.gateway
username = env.username
password = env.password

session = Hadoop.login( env.gateway, env.username, env.password )

tmpDir = "/user/${username}/test-${new Date().getTime()}"
tmpFile1 = "${tmpDir}/file1"
tmpFile2 = "${tmpDir}/file2"

// create a temporary dir
Hdfs.mkdir( session ).dir( tmpDir ).now()

// create a file from a string
Hdfs.put( session ).text( "some data" ).to( tmpFile1 ).now()

// upload a local file
Hdfs.put( session ).file( "example.txt" ).to( tmpFile2 ).now()

// remove the temporary dir
Hdfs.rm( session ).file( tmpDir ).recursive().now()

session.shutdown()
