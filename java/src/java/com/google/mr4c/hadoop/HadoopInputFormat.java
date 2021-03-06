/**
  * Copyright 2014 Google Inc. All rights reserved.
  * 
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  * 
  *     http://www.apache.org/licenses/LICENSE-2.0
  * 
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
*/

package com.google.mr4c.hadoop;

import com.google.mr4c.AlgoRunner;
import com.google.mr4c.sources.ExecutionSource;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.mapred.InputFormat;
import org.apache.hadoop.mapred.InputSplit;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.RecordReader;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.io.Text;

public class HadoopInputFormat implements InputFormat<Text,DataKeyList> {

	public RecordReader<Text,DataKeyList> getRecordReader(InputSplit split, JobConf job, Reporter reporter) throws IOException {
		HadoopUtils.initFromJob(job, true);
		return MR4CInputFormat.getRecordReader((MR4CInputSplit)split);
	}

	public InputSplit[] getSplits(JobConf job, int numSplits) throws IOException {
		ExecutionSource exeSrc = HadoopUtils.initFromJobAndCreateSource(job, false);
		return new MR4CInputFormat().getSplits(exeSrc, numSplits);

	}

}
