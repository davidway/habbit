/*
 * Copyright (c) 2017-2018 THL A29 Limited, a Tencent company. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tencentcloudapi.sqlserver.v20180328.models;

import com.tencentcloudapi.common.AbstractModel;
import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Expose;
import java.util.HashMap;

public class DescribeMigrationsResponse  extends AbstractModel{

    /**
    * 查询结果的总数
    */
    @SerializedName("TotalCount")
    @Expose
    private Integer TotalCount;

    /**
    * 查询结果的列表
    */
    @SerializedName("MigrateTaskSet")
    @Expose
    private MigrateTask [] MigrateTaskSet;

    /**
    * 唯一请求 ID，每次请求都会返回。定位问题时需要提供该次请求的 RequestId。
    */
    @SerializedName("RequestId")
    @Expose
    private String RequestId;

    /**
     * 获取查询结果的总数
     * @return TotalCount 查询结果的总数
     */
    public Integer getTotalCount() {
        return this.TotalCount;
    }

    /**
     * 设置查询结果的总数
     * @param TotalCount 查询结果的总数
     */
    public void setTotalCount(Integer TotalCount) {
        this.TotalCount = TotalCount;
    }

    /**
     * 获取查询结果的列表
     * @return MigrateTaskSet 查询结果的列表
     */
    public MigrateTask [] getMigrateTaskSet() {
        return this.MigrateTaskSet;
    }

    /**
     * 设置查询结果的列表
     * @param MigrateTaskSet 查询结果的列表
     */
    public void setMigrateTaskSet(MigrateTask [] MigrateTaskSet) {
        this.MigrateTaskSet = MigrateTaskSet;
    }

    /**
     * 获取唯一请求 ID，每次请求都会返回。定位问题时需要提供该次请求的 RequestId。
     * @return RequestId 唯一请求 ID，每次请求都会返回。定位问题时需要提供该次请求的 RequestId。
     */
    public String getRequestId() {
        return this.RequestId;
    }

    /**
     * 设置唯一请求 ID，每次请求都会返回。定位问题时需要提供该次请求的 RequestId。
     * @param RequestId 唯一请求 ID，每次请求都会返回。定位问题时需要提供该次请求的 RequestId。
     */
    public void setRequestId(String RequestId) {
        this.RequestId = RequestId;
    }

    /**
     * 内部实现，用户禁止调用
     */
    public void toMap(HashMap<String, String> map, String prefix) {
        this.setParamSimple(map, prefix + "TotalCount", this.TotalCount);
        this.setParamArrayObj(map, prefix + "MigrateTaskSet.", this.MigrateTaskSet);
        this.setParamSimple(map, prefix + "RequestId", this.RequestId);

    }
}

