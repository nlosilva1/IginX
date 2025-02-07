/*
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
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package cn.edu.tsinghua.iginx.query.entity;

import java.util.ArrayList;
import java.util.List;

public class RowRecord {

    private long timestamp;

    private List<Object> fields;

    public RowRecord(long timestamp) {
        this.timestamp = timestamp;
        this.fields = new ArrayList<>();
    }

    public RowRecord(long timestamp, List<Object> fields) {
        this.timestamp = timestamp;
        this.fields = fields;
    }

    public void addField(Object field) {
        fields.add(field);
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setFields(List<Object> fields) {
        this.fields = fields;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public List<Object> getFields() {
        return fields;
    }

    public void setField(int index, Object field) {
        this.fields.set(index, field);
    }

}
