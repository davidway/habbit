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
package com.tencentcloudapi.cdn.v20180606.models;

import com.tencentcloudapi.common.AbstractModel;
import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Expose;
import java.util.HashMap;

public class DescribePushTasksResponse  extends AbstractModel{

    /**
    * 预热历史记录
注意：此字段可能返回 null，表示取不到有效值。
    */
    @SerializedName("PushLogs")
    @Expose
    private PushTask [] PushLogs;

    /**
    * 任务总数，用于分页
注意：此字段可能返回 null，表示取不到有效值。
    */
    @SerializedName("TotalCount")
    @Expose
    private Integer TotalCount;

    /**
    * 唯一请求 ID，每次请求都会返回。定位问题时需要提供该次请求的 RequestId。
    */
    @SerializedName("RequestId")
    @Expose
    private String RequestId;

    /**
     * 获取预热历史记录
注意：此字段可能返回 null，表示取不到有效值。
     * @return PushLogs 预热历史记录
注意：此字段可能返回 null，表示取不到有效值。
     */
    public PushTask [] getPushLogs() {
        return this.PushLogs;
    }

    /**
     * 设置预热历史记录
注意：此字段可能返回 null，表示取不到有效值。
     * @param PushLogs 预热历史记录
注意：此字段可能返回 null，表示取不到有效值。
     */
    public void setPushLogs(PushTask [] PushLogs) {
        this.PushLogs = PushLogs;
    }

    /**
     * 获取任务总数，用于分页
注意：此字段可能返回 null，表示取不到有效值。
     * @return TotalCount 任务总数，用于分页
注意：此字段可能返回 null，表示取不到有效值。
     */
    public Integer getTotalCount() {
        return this.TotalCount;
    }

    /**
     * 设置任务总数，用于分页
注意：此字段可能返回 null，表示取不到有效值。
     * @param TotalCount 任务总数，用于分页
注意：此字段可能返回 null，表示取不到有效值。
     */
    public void setTotalCount(Integer TotalCount) {
        this.TotalCount = TotalCount;
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
        this.setParamArrayObj(map, prefix + "PushLogs.", this.PushLogs);
        this.setParamSimple(map, prefix + "TotalCount", this.TotalCount);
        this.setParamSimple(map, prefix + "RequestId", this.RequestId);

    }
}

