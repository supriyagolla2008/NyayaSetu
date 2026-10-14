import React from 'react';

export const LoadingSkeleton = ({ children, className = '' }: { children?: React.ReactNode, className?: string }) => {
  return <div className={`loadingskeleton ${className}`}>{children}</div>;
};
