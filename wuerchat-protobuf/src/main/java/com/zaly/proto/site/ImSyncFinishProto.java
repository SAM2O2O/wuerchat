// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: site/im_syncFinish.proto

package com.zaly.proto.site;

public final class ImSyncFinishProto {
  private ImSyncFinishProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface ImSyncFinishRequestOrBuilder extends
      // @@protoc_insertion_point(interface_extends:site.ImSyncFinishRequest)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>int64 p2p_pointer = 1;</code>
     */
    long getP2PPointer();

    /**
     * <code>map&lt;string, int64&gt; groups_pointer = 2;</code>
     */
    int getGroupsPointerCount();
    /**
     * <code>map&lt;string, int64&gt; groups_pointer = 2;</code>
     */
    boolean containsGroupsPointer(
        java.lang.String key);
    /**
     * Use {@link #getGroupsPointerMap()} instead.
     */
    @java.lang.Deprecated
    java.util.Map<java.lang.String, java.lang.Long>
    getGroupsPointer();
    /**
     * <code>map&lt;string, int64&gt; groups_pointer = 2;</code>
     */
    java.util.Map<java.lang.String, java.lang.Long>
    getGroupsPointerMap();
    /**
     * <code>map&lt;string, int64&gt; groups_pointer = 2;</code>
     */

    long getGroupsPointerOrDefault(
        java.lang.String key,
        long defaultValue);
    /**
     * <code>map&lt;string, int64&gt; groups_pointer = 2;</code>
     */

    long getGroupsPointerOrThrow(
        java.lang.String key);
  }
  /**
   * Protobuf type {@code site.ImSyncFinishRequest}
   */
  public  static final class ImSyncFinishRequest extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:site.ImSyncFinishRequest)
      ImSyncFinishRequestOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use ImSyncFinishRequest.newBuilder() to construct.
    private ImSyncFinishRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private ImSyncFinishRequest() {
      p2PPointer_ = 0L;
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private ImSyncFinishRequest(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownFieldProto3(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {

              p2PPointer_ = input.readInt64();
              break;
            }
            case 18: {
              if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
                groupsPointer_ = com.google.protobuf.MapField.newMapField(
                    GroupsPointerDefaultEntryHolder.defaultEntry);
                mutable_bitField0_ |= 0x00000002;
              }
              com.google.protobuf.MapEntry<java.lang.String, java.lang.Long>
              groupsPointer__ = input.readMessage(
                  GroupsPointerDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistry);
              groupsPointer_.getMutableMap().put(
                  groupsPointer__.getKey(), groupsPointer__.getValue());
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.zaly.proto.site.ImSyncFinishProto.internal_static_site_ImSyncFinishRequest_descriptor;
    }

    @SuppressWarnings({"rawtypes"})
    protected com.google.protobuf.MapField internalGetMapField(
        int number) {
      switch (number) {
        case 2:
          return internalGetGroupsPointer();
        default:
          throw new RuntimeException(
              "Invalid map field number: " + number);
      }
    }
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.zaly.proto.site.ImSyncFinishProto.internal_static_site_ImSyncFinishRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.zaly.proto.site.ImSyncFinishProto.ImSyncFinishRequest.class, com.zaly.proto.site.ImSyncFinishProto.ImSyncFinishRequest.Builder.class);
    }

    private int bitField0_;
    public static final int P2P_POINTER_FIELD_NUMBER = 1;
    private long p2PPointer_;
    /**
     * <code>int64 p2p_pointer = 1;</code>
     */
    public long getP2PPointer() {
      return p2PPointer_;
    }

    public static final int GROUPS_POINTER_FIELD_NUMBER = 2;
    private static final class GroupsPointerDefaultEntryHolder {
      static final com.google.protobuf.MapEntry<
          java.lang.String, java.lang.Long> defaultEntry =
              com.google.protobuf.MapEntry
              .<java.lang.String, java.lang.Long>newDefaultInstance(
                  com.zaly.proto.site.ImSyncFinishProto.internal_static_site_ImSyncFinishRequest_GroupsPointerEntry_descriptor, 
                  com.google.protobuf.WireFormat.FieldType.STRING,
                  "",
                  com.google.protobuf.WireFormat.FieldType.INT64,
                  0L);
    }
    private com.google.protobuf.MapField<
        java.lang.String, java.lang.Long> groupsPointer_;
    private com.google.protobuf.MapField<java.lang.String, java.lang.Long>
    internalGetGroupsPointer() {
      if (groupsPointer_ == null) {
        return com.google.protobuf.MapField.emptyMapField(
            GroupsPointerDefaultEntryHolder.defaultEntry);
      }
      return groupsPointer_;
    }

    public int getGroupsPointerCount() {
      return internalGetGroupsPointer().getMap().size();
    }
    /**
     * <code>map&lt;string, int64&gt; groups_pointer = 2;</code>
     */

    public boolean containsGroupsPointer(
        java.lang.String key) {
      if (key == null) { throw new java.lang.NullPointerException(); }
      return internalGetGroupsPointer().getMap().containsKey(key);
    }
    /**
     * Use {@link #getGroupsPointerMap()} instead.
     */
    @java.lang.Deprecated
    public java.util.Map<java.lang.String, java.lang.Long> getGroupsPointer() {
      return getGroupsPointerMap();
    }
    /**
     * <code>map&lt;string, int64&gt; groups_pointer = 2;</code>
     */

    public java.util.Map<java.lang.String, java.lang.Long> getGroupsPointerMap() {
      return internalGetGroupsPointer().getMap();
    }
    /**
     * <code>map&lt;string, int64&gt; groups_pointer = 2;</code>
     */

    public long getGroupsPointerOrDefault(
        java.lang.String key,
        long defaultValue) {
      if (key == null) { throw new java.lang.NullPointerException(); }
      java.util.Map<java.lang.String, java.lang.Long> map =
          internalGetGroupsPointer().getMap();
      return map.containsKey(key) ? map.get(key) : defaultValue;
    }
    /**
     * <code>map&lt;string, int64&gt; groups_pointer = 2;</code>
     */

    public long getGroupsPointerOrThrow(
        java.lang.String key) {
      if (key == null) { throw new java.lang.NullPointerException(); }
      java.util.Map<java.lang.String, java.lang.Long> map =
          internalGetGroupsPointer().getMap();
      if (!map.containsKey(key)) {
        throw new java.lang.IllegalArgumentException();
      }
      return map.get(key);
    }

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (p2PPointer_ != 0L) {
        output.writeInt64(1, p2PPointer_);
      }
      com.google.protobuf.GeneratedMessageV3
        .serializeStringMapTo(
          output,
          internalGetGroupsPointer(),
          GroupsPointerDefaultEntryHolder.defaultEntry,
          2);
      unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (p2PPointer_ != 0L) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(1, p2PPointer_);
      }
      for (java.util.Map.Entry<java.lang.String, java.lang.Long> entry
           : internalGetGroupsPointer().getMap().entrySet()) {
        com.google.protobuf.MapEntry<java.lang.String, java.lang.Long>
        groupsPointer__ = GroupsPointerDefaultEntryHolder.defaultEntry.newBuilderForType()
            .setKey(entry.getKey())
            .setValue(entry.getValue())
            .build();
        size += com.google.protobuf.CodedOutputStream
            .computeMessageSize(2, groupsPointer__);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof com.zaly.proto.site.ImSyncFinishProto.ImSyncFinishRequest)) {
        return super.equals(obj);
      }
      com.zaly.proto.site.ImSyncFinishProto.ImSyncFinishRequest other = (com.zaly.proto.site.ImSyncFinishProto.ImSyncFinishRequest) obj;

      boolean result = true;
      result = result && (getP2PPointer()
          == other.getP2PPointer());
      result = result && internalGetGroupsPointer().equals(
          other.internalGetGroupsPointer());
      result = result && unknownFields.equals(other.unknownFields);
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + P2P_POINTER_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getP2PPointer());
      if (!internalGetGroupsPointer().getMap().isEmpty()) {
        hash = (37 * hash) + GROUPS_POINTER_FIELD_NUMBER;
        hash = (53 * hash) + internalGetGroupsPointer().hashCode();
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.zaly.proto.site.ImSyncFinishProto.ImSyncFinishRequest parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.zaly.proto.site.ImSyncFinishProto.ImSyncFinishRequest parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.zaly.proto.site.ImSyncFinishProto.ImSyncFinishRequest parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.zaly.proto.site.ImSyncFinishProto.ImSyncFinishRequest parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.zaly.proto.site.ImSyncFinishProto.ImSyncFinishRequest parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.zaly.proto.site.ImSyncFinishProto.ImSyncFinishRequest parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.zaly.proto.site.ImSyncFinishProto.ImSyncFinishRequest parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.zaly.proto.site.ImSyncFinishProto.ImSyncFinishRequest parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.zaly.proto.site.ImSyncFinishProto.ImSyncFinishRequest parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.zaly.proto.site.ImSyncFinishProto.ImSyncFinishRequest parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.zaly.proto.site.ImSyncFinishProto.ImSyncFinishRequest parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.zaly.proto.site.ImSyncFinishProto.ImSyncFinishRequest parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.zaly.proto.site.ImSyncFinishProto.ImSyncFinishRequest prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code site.ImSyncFinishRequest}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:site.ImSyncFinishRequest)
        com.zaly.proto.site.ImSyncFinishProto.ImSyncFinishRequestOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.zaly.proto.site.ImSyncFinishProto.internal_static_site_ImSyncFinishRequest_descriptor;
      }

      @SuppressWarnings({"rawtypes"})
      protected com.google.protobuf.MapField internalGetMapField(
          int number) {
        switch (number) {
          case 2:
            return internalGetGroupsPointer();
          default:
            throw new RuntimeException(
                "Invalid map field number: " + number);
        }
      }
      @SuppressWarnings({"rawtypes"})
      protected com.google.protobuf.MapField internalGetMutableMapField(
          int number) {
        switch (number) {
          case 2:
            return internalGetMutableGroupsPointer();
          default:
            throw new RuntimeException(
                "Invalid map field number: " + number);
        }
      }
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.zaly.proto.site.ImSyncFinishProto.internal_static_site_ImSyncFinishRequest_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.zaly.proto.site.ImSyncFinishProto.ImSyncFinishRequest.class, com.zaly.proto.site.ImSyncFinishProto.ImSyncFinishRequest.Builder.class);
      }

      // Construct using com.zaly.proto.site.ImSyncFinishProto.ImSyncFinishRequest.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      public Builder clear() {
        super.clear();
        p2PPointer_ = 0L;

        internalGetMutableGroupsPointer().clear();
        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.zaly.proto.site.ImSyncFinishProto.internal_static_site_ImSyncFinishRequest_descriptor;
      }

      public com.zaly.proto.site.ImSyncFinishProto.ImSyncFinishRequest getDefaultInstanceForType() {
        return com.zaly.proto.site.ImSyncFinishProto.ImSyncFinishRequest.getDefaultInstance();
      }

      public com.zaly.proto.site.ImSyncFinishProto.ImSyncFinishRequest build() {
        com.zaly.proto.site.ImSyncFinishProto.ImSyncFinishRequest result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.zaly.proto.site.ImSyncFinishProto.ImSyncFinishRequest buildPartial() {
        com.zaly.proto.site.ImSyncFinishProto.ImSyncFinishRequest result = new com.zaly.proto.site.ImSyncFinishProto.ImSyncFinishRequest(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        result.p2PPointer_ = p2PPointer_;
        result.groupsPointer_ = internalGetGroupsPointer();
        result.groupsPointer_.makeImmutable();
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.setField(field, value);
      }
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.zaly.proto.site.ImSyncFinishProto.ImSyncFinishRequest) {
          return mergeFrom((com.zaly.proto.site.ImSyncFinishProto.ImSyncFinishRequest)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.zaly.proto.site.ImSyncFinishProto.ImSyncFinishRequest other) {
        if (other == com.zaly.proto.site.ImSyncFinishProto.ImSyncFinishRequest.getDefaultInstance()) return this;
        if (other.getP2PPointer() != 0L) {
          setP2PPointer(other.getP2PPointer());
        }
        internalGetMutableGroupsPointer().mergeFrom(
            other.internalGetGroupsPointer());
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.zaly.proto.site.ImSyncFinishProto.ImSyncFinishRequest parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.zaly.proto.site.ImSyncFinishProto.ImSyncFinishRequest) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private long p2PPointer_ ;
      /**
       * <code>int64 p2p_pointer = 1;</code>
       */
      public long getP2PPointer() {
        return p2PPointer_;
      }
      /**
       * <code>int64 p2p_pointer = 1;</code>
       */
      public Builder setP2PPointer(long value) {
        
        p2PPointer_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int64 p2p_pointer = 1;</code>
       */
      public Builder clearP2PPointer() {
        
        p2PPointer_ = 0L;
        onChanged();
        return this;
      }

      private com.google.protobuf.MapField<
          java.lang.String, java.lang.Long> groupsPointer_;
      private com.google.protobuf.MapField<java.lang.String, java.lang.Long>
      internalGetGroupsPointer() {
        if (groupsPointer_ == null) {
          return com.google.protobuf.MapField.emptyMapField(
              GroupsPointerDefaultEntryHolder.defaultEntry);
        }
        return groupsPointer_;
      }
      private com.google.protobuf.MapField<java.lang.String, java.lang.Long>
      internalGetMutableGroupsPointer() {
        onChanged();;
        if (groupsPointer_ == null) {
          groupsPointer_ = com.google.protobuf.MapField.newMapField(
              GroupsPointerDefaultEntryHolder.defaultEntry);
        }
        if (!groupsPointer_.isMutable()) {
          groupsPointer_ = groupsPointer_.copy();
        }
        return groupsPointer_;
      }

      public int getGroupsPointerCount() {
        return internalGetGroupsPointer().getMap().size();
      }
      /**
       * <code>map&lt;string, int64&gt; groups_pointer = 2;</code>
       */

      public boolean containsGroupsPointer(
          java.lang.String key) {
        if (key == null) { throw new java.lang.NullPointerException(); }
        return internalGetGroupsPointer().getMap().containsKey(key);
      }
      /**
       * Use {@link #getGroupsPointerMap()} instead.
       */
      @java.lang.Deprecated
      public java.util.Map<java.lang.String, java.lang.Long> getGroupsPointer() {
        return getGroupsPointerMap();
      }
      /**
       * <code>map&lt;string, int64&gt; groups_pointer = 2;</code>
       */

      public java.util.Map<java.lang.String, java.lang.Long> getGroupsPointerMap() {
        return internalGetGroupsPointer().getMap();
      }
      /**
       * <code>map&lt;string, int64&gt; groups_pointer = 2;</code>
       */

      public long getGroupsPointerOrDefault(
          java.lang.String key,
          long defaultValue) {
        if (key == null) { throw new java.lang.NullPointerException(); }
        java.util.Map<java.lang.String, java.lang.Long> map =
            internalGetGroupsPointer().getMap();
        return map.containsKey(key) ? map.get(key) : defaultValue;
      }
      /**
       * <code>map&lt;string, int64&gt; groups_pointer = 2;</code>
       */

      public long getGroupsPointerOrThrow(
          java.lang.String key) {
        if (key == null) { throw new java.lang.NullPointerException(); }
        java.util.Map<java.lang.String, java.lang.Long> map =
            internalGetGroupsPointer().getMap();
        if (!map.containsKey(key)) {
          throw new java.lang.IllegalArgumentException();
        }
        return map.get(key);
      }

      public Builder clearGroupsPointer() {
        internalGetMutableGroupsPointer().getMutableMap()
            .clear();
        return this;
      }
      /**
       * <code>map&lt;string, int64&gt; groups_pointer = 2;</code>
       */

      public Builder removeGroupsPointer(
          java.lang.String key) {
        if (key == null) { throw new java.lang.NullPointerException(); }
        internalGetMutableGroupsPointer().getMutableMap()
            .remove(key);
        return this;
      }
      /**
       * Use alternate mutation accessors instead.
       */
      @java.lang.Deprecated
      public java.util.Map<java.lang.String, java.lang.Long>
      getMutableGroupsPointer() {
        return internalGetMutableGroupsPointer().getMutableMap();
      }
      /**
       * <code>map&lt;string, int64&gt; groups_pointer = 2;</code>
       */
      public Builder putGroupsPointer(
          java.lang.String key,
          long value) {
        if (key == null) { throw new java.lang.NullPointerException(); }
        
        internalGetMutableGroupsPointer().getMutableMap()
            .put(key, value);
        return this;
      }
      /**
       * <code>map&lt;string, int64&gt; groups_pointer = 2;</code>
       */

      public Builder putAllGroupsPointer(
          java.util.Map<java.lang.String, java.lang.Long> values) {
        internalGetMutableGroupsPointer().getMutableMap()
            .putAll(values);
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFieldsProto3(unknownFields);
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:site.ImSyncFinishRequest)
    }

    // @@protoc_insertion_point(class_scope:site.ImSyncFinishRequest)
    private static final com.zaly.proto.site.ImSyncFinishProto.ImSyncFinishRequest DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.zaly.proto.site.ImSyncFinishProto.ImSyncFinishRequest();
    }

    public static com.zaly.proto.site.ImSyncFinishProto.ImSyncFinishRequest getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<ImSyncFinishRequest>
        PARSER = new com.google.protobuf.AbstractParser<ImSyncFinishRequest>() {
      public ImSyncFinishRequest parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
          return new ImSyncFinishRequest(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<ImSyncFinishRequest> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<ImSyncFinishRequest> getParserForType() {
      return PARSER;
    }

    public com.zaly.proto.site.ImSyncFinishProto.ImSyncFinishRequest getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_site_ImSyncFinishRequest_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_site_ImSyncFinishRequest_fieldAccessorTable;
  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_site_ImSyncFinishRequest_GroupsPointerEntry_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_site_ImSyncFinishRequest_GroupsPointerEntry_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\030site/im_syncFinish.proto\022\004site\032\017core/c" +
      "ore.proto\"\246\001\n\023ImSyncFinishRequest\022\023\n\013p2p" +
      "_pointer\030\001 \001(\003\022D\n\016groups_pointer\030\002 \003(\0132," +
      ".site.ImSyncFinishRequest.GroupsPointerE" +
      "ntry\0324\n\022GroupsPointerEntry\022\013\n\003key\030\001 \001(\t\022" +
      "\r\n\005value\030\002 \001(\003:\0028\0012R\n\023ImSyncFinishServic" +
      "e\022;\n\nsyncFinish\022\031.site.ImSyncFinishReque" +
      "st\032\022.core.NoneResponseB(\n\023com.zaly.proto" +
      ".siteB\021ImSyncFinishProtob\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.zaly.proto.core.CoreProto.getDescriptor(),
        }, assigner);
    internal_static_site_ImSyncFinishRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_site_ImSyncFinishRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_site_ImSyncFinishRequest_descriptor,
        new java.lang.String[] { "P2PPointer", "GroupsPointer", });
    internal_static_site_ImSyncFinishRequest_GroupsPointerEntry_descriptor =
      internal_static_site_ImSyncFinishRequest_descriptor.getNestedTypes().get(0);
    internal_static_site_ImSyncFinishRequest_GroupsPointerEntry_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_site_ImSyncFinishRequest_GroupsPointerEntry_descriptor,
        new java.lang.String[] { "Key", "Value", });
    com.zaly.proto.core.CoreProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
