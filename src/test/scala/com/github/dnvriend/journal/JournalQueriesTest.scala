package com.github.dnvriend.journal

import com.github.dnvriend.TestSpec

class JournalQueriesTest extends TestSpec {
  import profile.api._
  import akkaPersistenceRepository._

  "DDL" should "generate create table statement" in {
    JournalTable.schema.createStatements.toList.head shouldBe
      """create table "JDBC_JOURNAL" ("ordering" BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL,"deleted" BOOLEAN DEFAULT false NOT NULL,"persistence_id" VARCHAR(255) NOT NULL,"sequence_nr" BIGINT NOT NULL,"message" BLOB NOT NULL,"tags" VARCHAR(255))"""
  }

  it should "generate primary key statement" in {
    SnapshotTable.schema.createStatements.toList.drop(1).head shouldBe
      """alter table "JDBC_SNAPSHOTS" add constraint "snapshot_pk" primary key("persistence_id","sequence_nr")"""
  }
}